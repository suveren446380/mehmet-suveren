package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.local.QuestionsData
import com.example.data.local.QuizDatabase
import com.example.data.repository.QuizRepository
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.QuizNavy
import com.example.ui.viewmodel.QuizViewModel
import com.example.ui.viewmodel.QuizViewModelFactory
import com.example.ui.viewmodel.ScreenState
import com.example.util.SoundHapticManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        QuestionsData.init(applicationContext)

        val database = QuizDatabase.getInstance(applicationContext)
        val repository = QuizRepository(database.quizDao())
        val soundHaptic = SoundHapticManager(applicationContext)

        setContent {
            val factory = remember {
                QuizViewModelFactory(repository, soundHaptic)
            }
            val viewModel: QuizViewModel = viewModel(factory = factory)

            MyApplicationTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = QuizNavy
                ) {
                    QuizAppContent(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun QuizAppContent(viewModel: QuizViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
    val stagesProgress by viewModel.stages.collectAsStateWithLifecycle()
    val userStats by viewModel.userStats.collectAsStateWithLifecycle()
    val activeQuiz by viewModel.activeQuiz.collectAsStateWithLifecycle()
    val lastResult by viewModel.lastResultSummary.collectAsStateWithLifecycle()

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "ScreenTransition"
    ) { screen ->
        when (screen) {
            ScreenState.HOME -> {
                HomeScreen(
                    viewModel = viewModel,
                    stages = stagesProgress,
                    userStats = userStats
                )
            }
            ScreenState.STAGE_SELECT -> {
                StageSelectScreen(
                    viewModel = viewModel,
                    stagesProgress = stagesProgress
                )
            }
            ScreenState.QUIZ_PLAY -> {
                QuizPlayScreen(
                    viewModel = viewModel,
                    activeQuiz = activeQuiz
                )
            }
            ScreenState.STAGE_RESULT -> {
                StageResultScreen(
                    viewModel = viewModel,
                    summary = lastResult
                )
            }
            ScreenState.REVIEW -> {
                ReviewScreen(
                    viewModel = viewModel,
                    activeQuiz = activeQuiz
                )
            }
            ScreenState.STATS -> {
                StatsScreen(
                    viewModel = viewModel,
                    userStats = userStats,
                    stagesProgress = stagesProgress
                )
            }
        }
    }
}
