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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.entity.StageProgressEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.ui.theme.*
import com.example.ui.viewmodel.QuizViewModel
import com.example.ui.viewmodel.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    viewModel: QuizViewModel,
    userStats: UserStatsEntity,
    stagesProgress: List<StageProgressEntity>,
    modifier: Modifier = Modifier
) {
    var showResetDialog by remember { mutableStateOf(false) }

    BackHandler {
        viewModel.navigateTo(ScreenState.HOME)
    }

    val totalStagesCompleted = stagesProgress.count { it.isCompleted }
    val totalStars = stagesProgress.sumOf { it.starsEarned }
    val totalAnswered = userStats.totalCorrectAnswers + userStats.totalWrongAnswers
    val accuracy = if (totalAnswered > 0) (userStats.totalCorrectAnswers * 100) / totalAnswered else 0
    val hasPerfectStage = stagesProgress.any { it.bestCorrectCount == 10 }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = QuizNavy,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Başarılar ve İstatistikler",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateTo(ScreenState.HOME) },
                        modifier = Modifier.testTag("stats_back_btn")
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Summary Cards Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.EmojiEvents,
                    label = "Toplam Puan",
                    value = "${userStats.totalScore}",
                    accentColor = QuizGold
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Star,
                    label = "Yıldızlar",
                    value = "$totalStars / 30",
                    accentColor = QuizGoldBright
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Flag,
                    label = "Biten Etaplar",
                    value = "$totalStagesCompleted / 10",
                    accentColor = QuizIndigoLight
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Percent,
                    label = "Doğruluk Oranı",
                    value = "%$accuracy",
                    accentColor = QuizSuccessGreen
                )
            }

            // Detailed Breakdown
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = QuizSurfaceDark),
                border = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Cevap Özeti",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Toplam Doğru Cevap", color = QuizTextSecondary)
                        Text("${userStats.totalCorrectAnswers}", fontWeight = FontWeight.Bold, color = QuizSuccessGreen)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Toplam Yanlış / Boş", color = QuizTextSecondary)
                        Text("${userStats.totalWrongAnswers}", fontWeight = FontWeight.Bold, color = QuizErrorRed)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Toplam Oynanan Oyun", color = QuizTextSecondary)
                        Text("${userStats.totalGamesPlayed}", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }

            // Achievements / Badges Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = QuizSurfaceDark),
                border = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Kazanılan Rozetler",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    AchievementItem(
                        title = "İlk Adım",
                        description = "1. Etabı başarıyla tamamla",
                        isUnlocked = totalStagesCompleted >= 1,
                        icon = Icons.Default.DirectionsWalk
                    )

                    AchievementItem(
                        title = "Kusursuz Etap",
                        description = "Herhangi bir etapta 10'da 10 doğru yap",
                        isUnlocked = hasPerfectStage,
                        icon = Icons.Default.WorkspacePremium
                    )

                    AchievementItem(
                        title = "Bilgi Kurdu",
                        description = "İlk 5 etabı başarıyla tamamla",
                        isUnlocked = totalStagesCompleted >= 5,
                        icon = Icons.Default.MenuBook
                    )

                    AchievementItem(
                        title = "Yıldız Avcısı",
                        description = "En az 20 yıldız topla",
                        isUnlocked = totalStars >= 20,
                        icon = Icons.Default.AutoAwesome
                    )

                    AchievementItem(
                        title = "Büyük Şampiyon",
                        description = "10. Etabı ve tüm 100 soruyu tamamla",
                        isUnlocked = totalStagesCompleted == 10,
                        icon = Icons.Default.MilitaryTech
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Reset Button
            OutlinedButton(
                onClick = { showResetDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("reset_progress_btn"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = QuizErrorRed),
                border = androidx.compose.foundation.BorderStroke(1.dp, QuizErrorRed.copy(alpha = 0.6f))
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = null,
                    tint = QuizErrorRed
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Tüm İlerlemeyi Sıfırla")
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        showResetDialog = false
                        viewModel.resetAllProgress()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = QuizErrorRed)
                ) {
                    Text("Evet, Sıfırla")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Vazgeç", color = Color.White)
                }
            },
            title = {
                Text("İlerlemeyi Sıfırla?", color = Color.White, fontWeight = FontWeight.Bold)
            },
            text = {
                Text(
                    "Tüm etap kilitleri, kazanılan yıldızlar ve puanlar sıfırlanacak. Bu işlem geri alınamaz!",
                    color = QuizTextSecondary
                )
            },
            containerColor = QuizSurfaceDark,
            shape = RoundedCornerShape(20.dp)
        )
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    accentColor: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = QuizSurfaceDark),
        border = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(26.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = QuizTextSecondary
            )
        }
    }
}

@Composable
private fun AchievementItem(
    title: String,
    description: String,
    isUnlocked: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(
                    if (isUnlocked) QuizGold.copy(alpha = 0.2f) else QuizCardDark.copy(alpha = 0.3f)
                )
                .border(
                    1.dp,
                    if (isUnlocked) QuizGold else QuizBorder.copy(alpha = 0.3f),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isUnlocked) QuizGold else QuizTextSecondary.copy(alpha = 0.4f),
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = if (isUnlocked) Color.White else QuizTextSecondary.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = if (isUnlocked) QuizTextSecondary else QuizTextSecondary.copy(alpha = 0.4f)
            )
        }

        if (isUnlocked) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Kazanıldı",
                tint = QuizSuccessGreen,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Kilitli",
                tint = QuizTextSecondary.copy(alpha = 0.4f),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
