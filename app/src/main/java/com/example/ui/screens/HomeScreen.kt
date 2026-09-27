package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.data.local.entity.StageProgressEntity
import com.example.data.local.entity.UserStatsEntity
import com.example.ui.theme.*
import com.example.ui.viewmodel.QuizViewModel
import com.example.ui.viewmodel.ScreenState

@Composable
fun HomeScreen(
    viewModel: QuizViewModel,
    stages: List<StageProgressEntity>,
    userStats: UserStatsEntity,
    modifier: Modifier = Modifier
) {
    var showRulesDialog by remember { mutableStateOf(false) }
    val completedStagesCount = stages.count { it.isCompleted }
    val totalStars = stages.sumOf { it.starsEarned }
    val nextPlayableStage = stages.firstOrNull { it.isUnlocked && !it.isCompleted }?.stageNumber
        ?: stages.lastOrNull { it.isUnlocked }?.stageNumber ?: 1

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(QuizDeepBlue, QuizNavy, Color(0xFF090D16))
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Toolbar with Sound/Vibration toggles and Info
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = QuizSurfaceDark.copy(alpha = 0.8f),
                border = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Toplam Yıldız",
                        tint = QuizGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "$totalStars / 30",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(
                    onClick = { viewModel.toggleSound() },
                    modifier = Modifier
                        .size(44.dp)
                        .background(QuizSurfaceDark.copy(alpha = 0.8f), CircleShape)
                        .border(1.dp, QuizBorder, CircleShape)
                        .testTag("toggle_sound_btn")
                ) {
                    Icon(
                        imageVector = if (userStats.soundEnabled) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                        contentDescription = "Ses Aç/Kapat",
                        tint = if (userStats.soundEnabled) QuizGold else QuizTextSecondary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                IconButton(
                    onClick = { viewModel.toggleVibration() },
                    modifier = Modifier
                        .size(44.dp)
                        .background(QuizSurfaceDark.copy(alpha = 0.8f), CircleShape)
                        .border(1.dp, QuizBorder, CircleShape)
                        .testTag("toggle_vibration_btn")
                ) {
                    Icon(
                        imageVector = if (userStats.vibrationEnabled) Icons.Default.Vibration else Icons.Default.Smartphone,
                        contentDescription = "Titreşim Aç/Kapat",
                        tint = if (userStats.vibrationEnabled) QuizGold else QuizTextSecondary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                IconButton(
                    onClick = { showRulesDialog = true },
                    modifier = Modifier
                        .size(44.dp)
                        .background(QuizSurfaceDark.copy(alpha = 0.8f), CircleShape)
                        .border(1.dp, QuizBorder, CircleShape)
                        .testTag("rules_info_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.HelpOutline,
                        contentDescription = "Kurallar ve Bilgi",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Hero Emblem & Title
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(QuizGoldBright, QuizAmber, QuizIndigoDark)
                    )
                )
                .border(3.dp, QuizGold, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = "Kupa Logosu",
                tint = Color.White,
                modifier = Modifier.size(62.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "BİLGİ YARIŞI",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            letterSpacing = 2.sp
        )

        Text(
            text = "10 Etap • 1000 Soruluk Havuz • Kolaydan Zora",
            style = MaterialTheme.typography.titleMedium,
            color = QuizGoldBright,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Progress Overview Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = QuizSurfaceDark.copy(alpha = 0.9f)),
            border = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Genel İlerleme",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "$completedStagesCount / 10 Etap Tamamlandı",
                        style = MaterialTheme.typography.bodyMedium,
                        color = QuizGold,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                LinearProgressIndicator(
                    progress = { completedStagesCount / 10f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    color = QuizGold,
                    trackColor = QuizCardDark
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    MiniStatItem(
                        icon = Icons.Default.SportsScore,
                        label = "Toplam Puan",
                        value = "${userStats.totalScore}"
                    )
                    MiniStatItem(
                        icon = Icons.Default.CheckCircle,
                        label = "Doğru",
                        value = "${userStats.totalCorrectAnswers}"
                    )
                    val totalAnswered = userStats.totalCorrectAnswers + userStats.totalWrongAnswers
                    val accuracy = if (totalAnswered > 0) {
                        (userStats.totalCorrectAnswers * 100) / totalAnswered
                    } else 0
                    MiniStatItem(
                        icon = Icons.Default.Percent,
                        label = "İsabet Oranı",
                        value = "%$accuracy"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Main Action: Play Current Stage
        Button(
            onClick = { viewModel.startStage(nextPlayableStage) },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .testTag("play_primary_button"),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = QuizGold,
                contentColor = QuizNavy
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = if (completedStagesCount == 10) "1. Etaptan Tekrar Başla" else "Etap $nextPlayableStage'e Başla",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Secondary Action: Stage Map
        OutlinedButton(
            onClick = { viewModel.navigateTo(ScreenState.STAGE_SELECT) },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .testTag("stage_map_button"),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            ),
            border = androidx.compose.foundation.BorderStroke(1.5.dp, QuizIndigoLight)
        ) {
            Icon(
                imageVector = Icons.Default.Map,
                contentDescription = null,
                tint = QuizIndigoLight,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Tüm Etaplar Haritası (1 - 10)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tertiary Action: Trophy / Stats
        OutlinedButton(
            onClick = { viewModel.navigateTo(ScreenState.STATS) },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .testTag("trophy_stats_button"),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder)
        ) {
            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = null,
                tint = QuizGoldBright,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Başarılar ve İstatistikler",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }

    if (showRulesDialog) {
        AlertDialog(
            onDismissRequest = { showRulesDialog = false },
            confirmButton = {
                TextButton(onClick = { showRulesDialog = false }) {
                    Text("Anladım", color = QuizGold, fontWeight = FontWeight.Bold)
                }
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = QuizGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Yarışma Kuralları", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    RuleRow("🎯", "1000 Soru & 10 Etap", "Yarışma 10 etaptan oluşur ve 1000 soruluk dev havuzdan beslenir. Her etapta sorular kolaydan zora doğru ilerler.")
                    Spacer(modifier = Modifier.height(8.dp))
                    RuleRow("🔊", "Özel Sesler", "Doğru cevaplarda 'Baba pıro!', yanlış cevaplarda ise 2 saniyelik komik osuruk sesi çalar!")
                    Spacer(modifier = Modifier.height(8.dp))
                    RuleRow("🔓", "Etap Kilidi", "Bir sonraki etaba geçmek için mevcut etaptan en az 7 doğru (7/10) yapmalısınız.")
                    Spacer(modifier = Modifier.height(8.dp))
                    RuleRow("⭐", "Yıldız Sistemi", "5-6 doğru: 1 Yıldız • 7-8 doğru: 2 Yıldız • 9-10 doğru: 3 Yıldız.")
                    Spacer(modifier = Modifier.height(8.dp))
                    RuleRow("⏱️", "30 Saniye & Süre Bonusu", "Her soru için 30 saniye verilir. Hızlı cevaplar ekstra puan kazandırır!")
                    Spacer(modifier = Modifier.height(8.dp))
                    RuleRow("🃏", "Joker Hakları", "Her etapta %50, Seyirci Anketi, +15 Saniye Ek Süre ve Çift Cevap jokerlerinizi kullanabilirsiniz.")
                }
            },
            containerColor = QuizSurfaceDark,
            shape = RoundedCornerShape(20.dp)
        )
    }
}

@Composable
private fun MiniStatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = QuizIndigoLight,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
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

@Composable
private fun RuleRow(icon: String, title: String, description: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = icon, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 14.sp)
            Text(text = description, color = QuizTextSecondary, fontSize = 13.sp)
        }
    }
}
