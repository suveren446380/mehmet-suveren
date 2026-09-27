package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.ActiveQuizState
import com.example.ui.viewmodel.QuestionReviewItem
import com.example.ui.viewmodel.QuizViewModel
import com.example.ui.viewmodel.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    viewModel: QuizViewModel,
    activeQuiz: ActiveQuizState,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateTo(ScreenState.STAGE_RESULT)
    }

    val reviewItems = activeQuiz.sessionReviewList

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = QuizNavy,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Etap ${activeQuiz.stageNumber} Soruları",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(ScreenState.STAGE_RESULT) },
                        modifier = Modifier.testTag("review_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Geri",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = QuizDeepBlue
                )
            )
        }
    ) { paddingValues ->
        if (reviewItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "İncelenecek soru bulunamadı.",
                    color = QuizTextSecondary
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                itemsIndexed(reviewItems) { index, item ->
                    ReviewQuestionCard(
                        questionIndex = index + 1,
                        reviewItem = item
                    )
                }
            }
        }
    }
}

@Composable
private fun ReviewQuestionCard(
    questionIndex: Int,
    reviewItem: QuestionReviewItem
) {
    val q = reviewItem.question
    val letters = listOf("A", "B", "C", "D")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = QuizSurfaceDark),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (reviewItem.isCorrect) QuizSuccessGreen.copy(alpha = 0.6f) else QuizErrorRed.copy(alpha = 0.6f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Question number & Category
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                if (reviewItem.isCorrect) QuizSuccessGreen else QuizErrorRed
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (reviewItem.isCorrect) Icons.Default.CheckCircle else Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Soru $questionIndex",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = QuizCardDark
                ) {
                    Text(
                        text = q.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = QuizIndigoLight,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = q.question,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Options list with highlights
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                q.options.forEachIndexed { optIdx, optText ->
                    val isCorrectOption = optIdx == q.correctAnswerIndex
                    val isUserSelection = optIdx == reviewItem.selectedOptionIndex

                    val optBg = when {
                        isCorrectOption -> QuizSuccessBg
                        isUserSelection && !reviewItem.isCorrect -> QuizErrorBg
                        else -> QuizCardDark.copy(alpha = 0.4f)
                    }

                    val optBorder = when {
                        isCorrectOption -> QuizSuccessGreen
                        isUserSelection && !reviewItem.isCorrect -> QuizErrorRed
                        else -> QuizBorder.copy(alpha = 0.3f)
                    }

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = optBg,
                        border = androidx.compose.foundation.BorderStroke(1.dp, optBorder)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${letters.getOrElse(optIdx) { "" }})",
                                fontWeight = FontWeight.Bold,
                                color = if (isCorrectOption) QuizSuccessGreen else if (isUserSelection) QuizErrorRed else QuizTextSecondary,
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = optText,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                modifier = Modifier.weight(1f)
                            )
                            if (isCorrectOption) {
                                Text(
                                    text = "Doğru Cevap",
                                    color = QuizSuccessGreen,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            } else if (isUserSelection) {
                                Text(
                                    text = "Sizin Cevabınız",
                                    color = QuizErrorRed,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Educational insight
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = QuizIndigoDark.copy(alpha = 0.4f),
                border = androidx.compose.foundation.BorderStroke(1.dp, QuizIndigoLight.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "💡 Bilgi Notu:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = QuizGoldBright
                    )
                    Text(
                        text = q.explanation,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}
