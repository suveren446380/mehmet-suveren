package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.QuestionsData
import com.example.data.local.entity.StageProgressEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.data.model.LifelineType
import com.example.data.model.Question
import com.example.data.model.StageInfo
import com.example.data.repository.QuizRepository
import com.example.data.repository.StageResultSummary
import com.example.util.SoundHapticManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class ScreenState {
    HOME,
    STAGE_SELECT,
    QUIZ_PLAY,
    STAGE_RESULT,
    REVIEW,
    STATS
}

enum class QuestionPlayState {
    ACTIVE,
    REVEALED
}

data class QuestionReviewItem(
    val question: Question,
    val selectedOptionIndex: Int?,
    val isCorrect: Boolean,
    val timeSpentSeconds: Int
)

data class AudiencePollResult(
    val percentages: List<Int> // 4 items summing to 100
)

data class ActiveQuizState(
    val stageNumber: Int = 1,
    val currentQuestionIndex: Int = 0, // 0..9
    val currentQuestion: Question? = null,
    val playState: QuestionPlayState = QuestionPlayState.ACTIVE,
    val selectedAnswerIndex: Int? = null,
    val isAnswerCorrect: Boolean? = null,
    val remainingTimeSeconds: Int = 30,
    val totalTimeSpentSeconds: Int = 0,
    val currentScore: Int = 0,
    val currentCorrectCount: Int = 0,
    val currentStreak: Int = 0,
    val highestStreakInSession: Int = 0,
    // Lifelines used in this stage session
    val isFiftyFiftyUsed: Boolean = false,
    val isAudienceUsed: Boolean = false,
    val isExtraTimeUsed: Boolean = false,
    val isDoubleDipActive: Boolean = false,
    val isDoubleDipUsed: Boolean = false,
    // Options hidden by 50:50
    val hiddenOptionIndices: Set<Int> = emptySet(),
    // Audience poll result modal
    val audiencePoll: AudiencePollResult? = null,
    // Questions review list
    val sessionReviewList: List<QuestionReviewItem> = emptyList()
)

class QuizViewModel(
    private val repository: QuizRepository,
    private val soundHaptic: SoundHapticManager
) : ViewModel() {

    private val _currentScreen = MutableStateFlow(ScreenState.HOME)
    val currentScreen: StateFlow<ScreenState> = _currentScreen.asStateFlow()

    private val _stages = MutableStateFlow<List<StageProgressEntity>>(emptyList())
    val stages: StateFlow<List<StageProgressEntity>> = _stages.asStateFlow()

    private val _userStats = MutableStateFlow(UserStatsEntity())
    val userStats: StateFlow<UserStatsEntity> = _userStats.asStateFlow()

    private val _activeQuiz = MutableStateFlow(ActiveQuizState())
    val activeQuiz: StateFlow<ActiveQuizState> = _activeQuiz.asStateFlow()

    private val _lastResultSummary = MutableStateFlow<StageResultSummary?>(null)
    val lastResultSummary: StateFlow<StageResultSummary?> = _lastResultSummary.asStateFlow()

    private var timerJob: Job? = null
    private var stageQuestions: List<Question> = emptyList()
    private var questionStartTimeSeconds: Int = 30

    init {
        viewModelScope.launch {
            repository.initializeIfNeeded()
            launch {
                repository.allStageProgress.collect { list ->
                    _stages.value = list
                }
            }
            launch {
                repository.userStatsFlow.collect { stats ->
                    stats?.let { _userStats.value = it }
                }
            }
        }
    }

    fun navigateTo(screen: ScreenState) {
        soundHaptic.playClick(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
        _currentScreen.value = screen
    }

    fun startStage(stageNumber: Int) {
        stageQuestions = QuestionsData.getQuestionsForStage(stageNumber)
        if (stageQuestions.isEmpty()) return

        soundHaptic.playClick(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
        _activeQuiz.value = ActiveQuizState(
            stageNumber = stageNumber,
            currentQuestionIndex = 0,
            currentQuestion = stageQuestions[0],
            playState = QuestionPlayState.ACTIVE,
            remainingTimeSeconds = 30,
            sessionReviewList = emptyList()
        )
        _currentScreen.value = ScreenState.QUIZ_PLAY
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        questionStartTimeSeconds = _activeQuiz.value.remainingTimeSeconds
        timerJob = viewModelScope.launch {
            while (_activeQuiz.value.remainingTimeSeconds > 0 &&
                _activeQuiz.value.playState == QuestionPlayState.ACTIVE
            ) {
                delay(1000L)
                val newTime = _activeQuiz.value.remainingTimeSeconds - 1
                _activeQuiz.value = _activeQuiz.value.copy(
                    remainingTimeSeconds = newTime,
                    totalTimeSpentSeconds = _activeQuiz.value.totalTimeSpentSeconds + 1
                )
                if (newTime == 0) {
                    onTimeOut()
                    break
                }
            }
        }
    }

    private fun onTimeOut() {
        val state = _activeQuiz.value
        val question = state.currentQuestion ?: return
        soundHaptic.playWrong(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)

        val updatedReview = state.sessionReviewList + QuestionReviewItem(
            question = question,
            selectedOptionIndex = null,
            isCorrect = false,
            timeSpentSeconds = 30
        )

        _activeQuiz.value = state.copy(
            playState = QuestionPlayState.REVEALED,
            selectedAnswerIndex = null,
            isAnswerCorrect = false,
            currentStreak = 0,
            sessionReviewList = updatedReview
        )
    }

    fun selectOption(optionIndex: Int) {
        val state = _activeQuiz.value
        if (state.playState != QuestionPlayState.ACTIVE) return
        if (state.hiddenOptionIndices.contains(optionIndex)) return

        val question = state.currentQuestion ?: return
        val isCorrect = optionIndex == question.correctAnswerIndex

        if (!isCorrect && state.isDoubleDipActive) {
            // Used first dip of Double Dip!
            soundHaptic.playWrong(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
            _activeQuiz.value = state.copy(
                isDoubleDipActive = false,
                hiddenOptionIndices = state.hiddenOptionIndices + optionIndex
            )
            return
        }

        timerJob?.cancel()

        val timeSpent = questionStartTimeSeconds - state.remainingTimeSeconds
        val newCorrectCount = if (isCorrect) state.currentCorrectCount + 1 else state.currentCorrectCount
        val newStreak = if (isCorrect) state.currentStreak + 1 else 0
        val highestStreak = maxOf(state.highestStreakInSession, newStreak)

        // Point calculation: Base question points + speed bonus + streak multiplier
        val streakMultiplier = when {
            newStreak >= 5 -> 2.0f
            newStreak >= 3 -> 1.5f
            newStreak >= 2 -> 1.2f
            else -> 1.0f
        }
        val speedBonus = if (isCorrect) (state.remainingTimeSeconds * 5) else 0
        val earnedPoints = if (isCorrect) {
            ((question.points * streakMultiplier).toInt() + speedBonus)
        } else {
            0
        }

        if (isCorrect) {
            soundHaptic.playCorrect(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
        } else {
            soundHaptic.playWrong(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
        }

        val updatedReview = state.sessionReviewList + QuestionReviewItem(
            question = question,
            selectedOptionIndex = optionIndex,
            isCorrect = isCorrect,
            timeSpentSeconds = timeSpent
        )

        _activeQuiz.value = state.copy(
            playState = QuestionPlayState.REVEALED,
            selectedAnswerIndex = optionIndex,
            isAnswerCorrect = isCorrect,
            currentScore = state.currentScore + earnedPoints,
            currentCorrectCount = newCorrectCount,
            currentStreak = newStreak,
            highestStreakInSession = highestStreak,
            sessionReviewList = updatedReview
        )
    }

    fun nextQuestionOrFinish() {
        soundHaptic.playClick(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
        val state = _activeQuiz.value
        val nextIdx = state.currentQuestionIndex + 1

        if (nextIdx < stageQuestions.size) {
            // Next question
            _activeQuiz.value = state.copy(
                currentQuestionIndex = nextIdx,
                currentQuestion = stageQuestions[nextIdx],
                playState = QuestionPlayState.ACTIVE,
                selectedAnswerIndex = null,
                isAnswerCorrect = null,
                remainingTimeSeconds = 30,
                hiddenOptionIndices = emptySet(),
                isDoubleDipActive = false,
                audiencePoll = null
            )
            startTimer()
        } else {
            // Stage completed!
            finishStage()
        }
    }

    private fun finishStage() {
        val state = _activeQuiz.value
        timerJob?.cancel()

        soundHaptic.playStageResultSound(
            correctCount = state.currentCorrectCount,
            soundEnabled = _userStats.value.soundEnabled,
            vibrationEnabled = _userStats.value.vibrationEnabled
        )

        viewModelScope.launch {
            val result = repository.recordStageResult(
                stageNumber = state.stageNumber,
                correctCount = state.currentCorrectCount,
                totalScoreEarned = state.currentScore,
                timeSeconds = state.totalTimeSpentSeconds
            )
            _lastResultSummary.value = result
            _currentScreen.value = ScreenState.STAGE_RESULT
        }
    }

    fun replayStageVoice() {
        val summary = _lastResultSummary.value ?: return
        soundHaptic.playStageResultSound(
            correctCount = summary.correctCount,
            soundEnabled = _userStats.value.soundEnabled,
            vibrationEnabled = _userStats.value.vibrationEnabled
        )
    }

    // Lifeline 1: 50%
    fun useFiftyFifty() {
        val state = _activeQuiz.value
        if (state.isFiftyFiftyUsed || state.playState != QuestionPlayState.ACTIVE) return
        val question = state.currentQuestion ?: return

        soundHaptic.playLifeline(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
        val wrongIndices = listOf(0, 1, 2, 3).filter { it != question.correctAnswerIndex }.shuffled()
        val toHide = wrongIndices.take(2).toSet()

        _activeQuiz.value = state.copy(
            isFiftyFiftyUsed = true,
            hiddenOptionIndices = toHide
        )
    }

    // Lifeline 2: Audience Poll
    fun useAudiencePoll() {
        val state = _activeQuiz.value
        if (state.isAudienceUsed || state.playState != QuestionPlayState.ACTIVE) return
        val question = state.currentQuestion ?: return

        soundHaptic.playLifeline(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)

        // Realistic audience distribution with high chance on correct answer depending on stage difficulty
        val correctIndex = question.correctAnswerIndex
        val difficultyWeight = (11 - question.stage) // stages 1..10 -> weight 10..1
        val correctPct = minOf(88, maxOf(45, 40 + (difficultyWeight * 4)))
        val remainingPct = 100 - correctPct

        val wrong1 = (remainingPct * 0.5f).toInt()
        val wrong2 = (remainingPct * 0.3f).toInt()
        val wrong3 = remainingPct - wrong1 - wrong2

        val wrongPcts = listOf(wrong1, wrong2, wrong3).shuffled()
        var wIdx = 0
        val pcts = (0..3).map { idx ->
            if (idx == correctIndex) correctPct else wrongPcts[wIdx++]
        }

        _activeQuiz.value = state.copy(
            isAudienceUsed = true,
            audiencePoll = AudiencePollResult(pcts)
        )
    }

    fun dismissAudiencePoll() {
        _activeQuiz.value = _activeQuiz.value.copy(audiencePoll = null)
    }

    // Lifeline 3: Extra Time (+15 sec)
    fun useExtraTime() {
        val state = _activeQuiz.value
        if (state.isExtraTimeUsed || state.playState != QuestionPlayState.ACTIVE) return

        soundHaptic.playLifeline(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
        _activeQuiz.value = state.copy(
            isExtraTimeUsed = true,
            remainingTimeSeconds = state.remainingTimeSeconds + 15
        )
    }

    // Lifeline 4: Double Dip (Çift Cevap)
    fun useDoubleDip() {
        val state = _activeQuiz.value
        if (state.isDoubleDipUsed || state.playState != QuestionPlayState.ACTIVE) return

        soundHaptic.playLifeline(_userStats.value.soundEnabled, _userStats.value.vibrationEnabled)
        _activeQuiz.value = state.copy(
            isDoubleDipUsed = true,
            isDoubleDipActive = true
        )
    }

    fun toggleSound() {
        val newSound = !_userStats.value.soundEnabled
        soundHaptic.playClick(newSound, _userStats.value.vibrationEnabled)
        viewModelScope.launch {
            repository.updateSettings(
                soundEnabled = newSound,
                vibrationEnabled = _userStats.value.vibrationEnabled
            )
        }
    }

    fun toggleVibration() {
        val newVibe = !_userStats.value.vibrationEnabled
        soundHaptic.playClick(_userStats.value.soundEnabled, newVibe)
        viewModelScope.launch {
            repository.updateSettings(
                soundEnabled = _userStats.value.soundEnabled,
                vibrationEnabled = newVibe
            )
        }
    }

    fun resetAllProgress() {
        viewModelScope.launch {
            repository.resetAllProgress()
            _activeQuiz.value = ActiveQuizState()
            _currentScreen.value = ScreenState.HOME
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
        soundHaptic.release()
    }
}
