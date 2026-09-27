package com.example.data.repository

import com.example.data.local.dao.QuizDao
import com.example.data.local.entity.StageProgressEntity
import com.example.data.local.entity.UserStatsEntity
import kotlinx.coroutines.flow.Flow

class QuizRepository(private val quizDao: QuizDao) {

    val allStageProgress: Flow<List<StageProgressEntity>> = quizDao.getAllStageProgress()
    val userStatsFlow: Flow<UserStatsEntity?> = quizDao.getUserStatsFlow()

    suspend fun initializeIfNeeded() {
        val currentStats = quizDao.getUserStats()
        if (currentStats == null) {
            quizDao.insertOrUpdateUserStats(UserStatsEntity())
        }

        val stage1 = quizDao.getStageProgress(1)
        if (stage1 == null) {
            val initialStages = (1..10).map { stageNum ->
                StageProgressEntity(
                    stageNumber = stageNum,
                    isUnlocked = stageNum == 1, // Only Stage 1 is unlocked initially
                    isCompleted = false,
                    starsEarned = 0,
                    bestScore = 0,
                    bestCorrectCount = 0,
                    bestTimeSeconds = 0
                )
            }
            quizDao.insertStageProgressList(initialStages)
        }
    }

    suspend fun recordStageResult(
        stageNumber: Int,
        correctCount: Int,
        totalScoreEarned: Int,
        timeSeconds: Int
    ): StageResultSummary {
        val currentStage = quizDao.getStageProgress(stageNumber) ?: StageProgressEntity(
            stageNumber = stageNumber,
            isUnlocked = true
        )

        val passed = correctCount >= 7
        val stars = when {
            correctCount >= 9 -> 3
            correctCount >= 7 -> 2
            correctCount >= 5 -> 1
            else -> 0
        }

        val updatedCurrent = currentStage.copy(
            isCompleted = currentStage.isCompleted || passed,
            starsEarned = maxOf(currentStage.starsEarned, stars),
            bestScore = maxOf(currentStage.bestScore, totalScoreEarned),
            bestCorrectCount = maxOf(currentStage.bestCorrectCount, correctCount),
            bestTimeSeconds = if (currentStage.bestTimeSeconds == 0) timeSeconds else minOf(currentStage.bestTimeSeconds, timeSeconds),
            lastCompletedAt = System.currentTimeMillis()
        )
        quizDao.insertOrUpdateStageProgress(updatedCurrent)

        var nextStageUnlocked = false
        if (passed && stageNumber < 10) {
            val nextStageNum = stageNumber + 1
            val nextStage = quizDao.getStageProgress(nextStageNum) ?: StageProgressEntity(
                stageNumber = nextStageNum
            )
            if (!nextStage.isUnlocked) {
                quizDao.insertOrUpdateStageProgress(nextStage.copy(isUnlocked = true))
                nextStageUnlocked = true
            }
        }

        // Update overall user stats
        val currentStats = quizDao.getUserStats() ?: UserStatsEntity()
        val wrongCount = 10 - correctCount
        val newCompletedCount = if (passed && !currentStage.isCompleted) {
            currentStats.stagesCompletedCount + 1
        } else {
            currentStats.stagesCompletedCount
        }

        val updatedStats = currentStats.copy(
            totalScore = currentStats.totalScore + totalScoreEarned,
            totalGamesPlayed = currentStats.totalGamesPlayed + 1,
            totalCorrectAnswers = currentStats.totalCorrectAnswers + correctCount,
            totalWrongAnswers = currentStats.totalWrongAnswers + wrongCount,
            stagesCompletedCount = newCompletedCount
        )
        quizDao.insertOrUpdateUserStats(updatedStats)

        return StageResultSummary(
            stageNumber = stageNumber,
            passed = passed,
            stars = stars,
            score = totalScoreEarned,
            correctCount = correctCount,
            wrongCount = wrongCount,
            timeSeconds = timeSeconds,
            nextStageUnlocked = nextStageUnlocked
        )
    }

    suspend fun updateSettings(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        val stats = quizDao.getUserStats() ?: UserStatsEntity()
        quizDao.insertOrUpdateUserStats(
            stats.copy(soundEnabled = soundEnabled, vibrationEnabled = vibrationEnabled)
        )
    }

    suspend fun resetAllProgress() {
        quizDao.clearStageProgress()
        quizDao.clearUserStats()
        initializeIfNeeded()
    }
}

data class StageResultSummary(
    val stageNumber: Int,
    val passed: Boolean,
    val stars: Int,
    val score: Int,
    val correctCount: Int,
    val wrongCount: Int,
    val timeSeconds: Int,
    val nextStageUnlocked: Boolean
)
