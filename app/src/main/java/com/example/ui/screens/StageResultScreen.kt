package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.StageResultSummary
import com.example.ui.theme.*
import com.example.ui.viewmodel.QuizViewModel
import com.example.ui.viewmodel.ScreenState

@Composable
fun StageResultScreen(
    viewModel: QuizViewModel,
    summary: StageResultSummary?,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateTo(ScreenState.STAGE_SELECT)
    }

    if (summary == null) {
        viewModel.navigateTo(ScreenState.HOME)
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(QuizDeepBlue, QuizNavy, Color(0xFF090D16))
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Big Icon Emblem
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(
                    if (summary.passed) {
                        Brush.radialGradient(listOf(QuizGoldBright, QuizAmber, QuizNavy))
                    } else {
                        Brush.radialGradient(listOf(QuizErrorRed.copy(alpha = 0.5f), QuizCardDark, QuizNavy))
                    }
                )
                .border(
                    3.dp,
                    if (summary.passed) QuizGold else QuizErrorRed,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (summary.passed) Icons.Default.EmojiEvents else Icons.Default.Replay,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(54.dp)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = if (summary.passed) "TEBRİKLER!" else "TEKRAR DENE!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = if (summary.passed) QuizGoldBright else QuizTextPrimary,
            letterSpacing = 1.sp
        )

        Text(
            text = if (summary.passed) {
                "Etap ${summary.stageNumber} başarıyla tamamlandı!"
            } else {
                "Etap ${summary.stageNumber} geçilemedi (En az 7 doğru gerekli)."
            },
            style = MaterialTheme.typography.bodyLarge,
            color = QuizTextSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stars Row
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { starIndex ->
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (starIndex < summary.stars) QuizGold else QuizCardDark,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Next Stage Unlocked Banner
        if (summary.nextStageUnlocked && summary.stageNumber < 10) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = QuizSuccessBg,
                border = androidx.compose.foundation.BorderStroke(1.5.dp, QuizSuccessGreen)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LockOpen,
                        contentDescription = null,
                        tint = QuizGold,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "YENİ ETAP KİLİDİ AÇILDI!",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Etap ${summary.stageNumber + 1} artık erişilebilir.",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 13.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
        }

        // Stats Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = QuizSurfaceDark),
            border = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                ResultStatRow("Kazanılan Puan", "+${summary.score}", QuizGold)
                HorizontalDivider(color = QuizBorder.copy(alpha = 0.5f))
                ResultStatRow("Doğru Sayısı", "${summary.correctCount} / 10", QuizSuccessGreen)
                HorizontalDivider(color = QuizBorder.copy(alpha = 0.5f))
                ResultStatRow("Yanlış / Boş", "${summary.wrongCount} / 10", QuizErrorRed)
                HorizontalDivider(color = QuizBorder.copy(alpha = 0.5f))
                val minutes = summary.timeSeconds / 60
                val seconds = summary.timeSeconds % 60
                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                ResultStatRow("Toplam Süre", formattedTime, QuizIndigoLight)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        if (summary.passed && summary.stageNumber < 10) {
            Button(
                onClick = { viewModel.startStage(summary.stageNumber + 1) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("result_next_stage_btn"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = QuizGold,
                    contentColor = QuizNavy
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Sonraki Etap (${summary.stageNumber + 1}) ➡️",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = { viewModel.startStage(summary.stageNumber) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("result_retry_stage_btn"),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (summary.passed) QuizCardDark else QuizGold,
                contentColor = if (summary.passed) Color.White else QuizNavy
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Bu Etabı Tekrar Oyna",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { viewModel.navigateTo(ScreenState.REVIEW) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("result_review_answers_btn"),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, QuizIndigoLight)
        ) {
            Icon(imageVector = Icons.Default.MenuBook, contentDescription = null, tint = QuizIndigoLight)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Soruları ve Açıklamaları İncele",
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = { viewModel.navigateTo(ScreenState.STAGE_SELECT) },
            modifier = Modifier.testTag("result_back_stages_btn")
        ) {
            Text(
                text = "Etap Haritasına Dön",
                color = QuizTextSecondary,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ResultStatRow(label: String, value: String, valueColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = QuizTextSecondary)
        Text(text = value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = valueColor)
    }
}
