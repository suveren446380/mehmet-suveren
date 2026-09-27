package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.QuestionsData
import com.example.data.local.entity.StageProgressEntity
import com.example.data.model.StageInfo
import com.example.ui.theme.*
import com.example.ui.viewmodel.QuizViewModel
import com.example.ui.viewmodel.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StageSelectScreen(
    viewModel: QuizViewModel,
    stagesProgress: List<StageProgressEntity>,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateTo(ScreenState.HOME)
    }

    val stagesInfo = QuestionsData.stages
    val progressMap = stagesProgress.associateBy { it.stageNumber }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = QuizNavy,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Etap Haritası (10 Etap)",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(ScreenState.HOME) },
                        modifier = Modifier.testTag("stage_select_back_btn")
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(stagesInfo) { stageInfo ->
                val progress = progressMap[stageInfo.stageNumber]
                val isUnlocked = progress?.isUnlocked ?: (stageInfo.stageNumber == 1)
                val isCompleted = progress?.isCompleted ?: false
                val stars = progress?.starsEarned ?: 0
                val bestScore = progress?.bestScore ?: 0

                StageCard(
                    stageInfo = stageInfo,
                    isUnlocked = isUnlocked,
                    isCompleted = isCompleted,
                    stars = stars,
                    bestScore = bestScore,
                    onStageClick = {
                        if (isUnlocked) {
                            viewModel.startStage(stageInfo.stageNumber)
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
private fun StageCard(
    stageInfo: StageInfo,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    stars: Int,
    bestScore: Int,
    onStageClick: () -> Unit
) {
    val difficultyColor = when (stageInfo.stageNumber) {
        1, 2 -> QuizSuccessGreen
        3, 4 -> Color(0xFF0EA5E9)
        5, 6 -> QuizGold
        7, 8 -> QuizAmber
        9, 10 -> QuizErrorRed
        else -> QuizIndigoLight
    }

    val cardBorder = if (isUnlocked) {
        if (isCompleted) QuizGold else QuizIndigoLight
    } else {
        QuizBorder.copy(alpha = 0.5f)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isUnlocked) { onStageClick() }
            .testTag("stage_card_${stageInfo.stageNumber}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) QuizSurfaceDark else QuizCardDark.copy(alpha = 0.4f)
        ),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, cardBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Stage Number Badge / Lock Icon
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(
                        if (isUnlocked) {
                            Brush.linearGradient(listOf(difficultyColor.copy(alpha = 0.3f), QuizIndigoDark))
                        } else {
                            Brush.linearGradient(listOf(QuizCardDark, Color(0xFF1E293B)))
                        }
                    )
                    .border(
                        2.dp,
                        if (isUnlocked) difficultyColor else QuizBorder,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isUnlocked) {
                    Text(
                        text = "${stageInfo.stageNumber}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp,
                        color = Color.White
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Kilitli",
                        tint = QuizTextSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Stage Info Column
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stageInfo.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isUnlocked) Color.White else QuizTextSecondary
                    )
                }

                Text(
                    text = stageInfo.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isUnlocked) QuizTextSecondary else QuizTextSecondary.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Difficulty pill
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = difficultyColor.copy(alpha = 0.2f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, difficultyColor.copy(alpha = 0.6f))
                    ) {
                        Text(
                            text = stageInfo.difficultyLabel,
                            style = MaterialTheme.typography.labelSmall,
                            color = difficultyColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }

                    // Points per question
                    Text(
                        text = "${stageInfo.pointPerQuestion} Puan/Soru",
                        style = MaterialTheme.typography.labelSmall,
                        color = QuizGoldBright
                    )
                }
            }

            // Right side: Stars or Locked state
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                if (isUnlocked) {
                    Row {
                        repeat(3) { index ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = if (index < stars) QuizGold else QuizBorder,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    if (bestScore > 0) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "En İyi: $bestScore",
                            style = MaterialTheme.typography.labelSmall,
                            color = QuizTextSecondary
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Icon(
                        imageVector = Icons.Default.PlayCircleOutline,
                        contentDescription = "Oyna",
                        tint = QuizGold,
                        modifier = Modifier.size(26.dp)
                    )
                } else {
                    Text(
                        text = "Kilitli",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = QuizTextSecondary
                    )
                    Text(
                        text = "Önceki: min 7/10",
                        style = MaterialTheme.typography.labelSmall,
                        color = QuizTextSecondary.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
