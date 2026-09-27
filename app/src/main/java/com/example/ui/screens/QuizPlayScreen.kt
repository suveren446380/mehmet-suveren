package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Question
import com.example.ui.theme.*
import com.example.ui.viewmodel.ActiveQuizState
import com.example.ui.viewmodel.QuestionPlayState
import com.example.ui.viewmodel.QuizViewModel
import com.example.ui.viewmodel.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizPlayScreen(
    viewModel: QuizViewModel,
    activeQuiz: ActiveQuizState,
    modifier: Modifier = Modifier
) {
    var showQuitDialog by remember { mutableStateOf(false) }

    BackHandler {
        showQuitDialog = true
    }

    val question = activeQuiz.currentQuestion ?: return
    val isRevealed = activeQuiz.playState == QuestionPlayState.REVEALED

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = QuizNavy,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Etap ${activeQuiz.stageNumber}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Soru ${activeQuiz.currentQuestionIndex + 1} / 10",
                            style = MaterialTheme.typography.bodySmall,
                            color = QuizGoldBright
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { showQuitDialog = true },
                        modifier = Modifier.testTag("quiz_quit_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Geri",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    // Score chip
                    Surface(
                        shape = RoundedCornerShape(14.dp),
                        color = QuizSurfaceDark,
                        border = androidx.compose.foundation.BorderStroke(1.dp, QuizGold.copy(alpha = 0.6f)),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.MonetizationOn,
                                contentDescription = null,
                                tint = QuizGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${activeQuiz.currentScore}",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
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
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Timer & Streak Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Timer
                val timerColor = when {
                    activeQuiz.remainingTimeSeconds <= 5 -> QuizErrorRed
                    activeQuiz.remainingTimeSeconds <= 10 -> QuizAmber
                    else -> QuizSuccessGreen
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = "Kalan Süre",
                        tint = timerColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${activeQuiz.remainingTimeSeconds} sn",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = timerColor
                    )
                }

                // Combo Streak indicator
                if (activeQuiz.currentStreak >= 2) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = QuizAmber.copy(alpha = 0.2f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, QuizAmber)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🔥", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            val multiplier = when {
                                activeQuiz.currentStreak >= 5 -> "2.0x"
                                activeQuiz.currentStreak >= 3 -> "1.5x"
                                else -> "1.2x"
                            }
                            Text(
                                text = "${activeQuiz.currentStreak} Seri ($multiplier)",
                                fontWeight = FontWeight.Bold,
                                color = QuizAmber,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Timer Linear Progress Bar
            LinearProgressIndicator(
                progress = { (activeQuiz.remainingTimeSeconds / 30f).coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = when {
                    activeQuiz.remainingTimeSeconds <= 5 -> QuizErrorRed
                    activeQuiz.remainingTimeSeconds <= 10 -> QuizAmber
                    else -> QuizSuccessGreen
                },
                trackColor = QuizCardDark
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Lifelines Row (Jokerler)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LifelineButton(
                    title = "%50",
                    subtitle = "Yarı Yarıya",
                    icon = Icons.Default.Filter2,
                    isUsed = activeQuiz.isFiftyFiftyUsed,
                    isEnabled = !activeQuiz.isFiftyFiftyUsed && !isRevealed,
                    onClick = { viewModel.useFiftyFifty() },
                    tag = "lifeline_fifty_fifty"
                )

                LifelineButton(
                    title = "Seyirci",
                    subtitle = "Anketi",
                    icon = Icons.Default.Groups,
                    isUsed = activeQuiz.isAudienceUsed,
                    isEnabled = !activeQuiz.isAudienceUsed && !isRevealed,
                    onClick = { viewModel.useAudiencePoll() },
                    tag = "lifeline_audience"
                )

                LifelineButton(
                    title = "+15 Sn",
                    subtitle = "Ek Süre",
                    icon = Icons.Default.MoreTime,
                    isUsed = activeQuiz.isExtraTimeUsed,
                    isEnabled = !activeQuiz.isExtraTimeUsed && !isRevealed,
                    onClick = { viewModel.useExtraTime() },
                    tag = "lifeline_extra_time"
                )

                LifelineButton(
                    title = "Çift Hak",
                    subtitle = if (activeQuiz.isDoubleDipActive) "Aktif!" else "Çift Şans",
                    icon = Icons.Default.LooksTwo,
                    isUsed = activeQuiz.isDoubleDipUsed,
                    isEnabled = !activeQuiz.isDoubleDipUsed && !isRevealed,
                    isActive = activeQuiz.isDoubleDipActive,
                    onClick = { viewModel.useDoubleDip() },
                    tag = "lifeline_double_dip"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Question Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = QuizSurfaceDark),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, QuizBorder)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = QuizIndigo.copy(alpha = 0.3f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, QuizIndigoLight)
                        ) {
                            Text(
                                text = question.category,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = QuizIndigoLight,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        Text(
                            text = "+${question.points} Puan",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = QuizGold
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = question.question,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 18.sp,
                        lineHeight = 26.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Options List
            val optionLetters = listOf("A", "B", "C", "D")
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                question.options.forEachIndexed { index, optionText ->
                    val isHidden = activeQuiz.hiddenOptionIndices.contains(index)
                    val isSelected = activeQuiz.selectedAnswerIndex == index
                    val isCorrectOption = index == question.correctAnswerIndex

                    QuizOptionCard(
                        letter = optionLetters.getOrElse(index) { "?" },
                        text = optionText,
                        isHidden = isHidden,
                        isSelected = isSelected,
                        isRevealed = isRevealed,
                        isCorrect = isCorrectOption,
                        onClick = {
                            if (!isRevealed && !isHidden) {
                                viewModel.selectOption(index)
                            }
                        },
                        tag = "option_button_$index"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Answer Reveal & Explanation Box
            AnimatedVisibility(
                visible = isRevealed,
                enter = fadeIn() + expandVertically()
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (activeQuiz.isAnswerCorrect == true) QuizSuccessBg else QuizErrorBg
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        1.5.dp,
                        if (activeQuiz.isAnswerCorrect == true) QuizSuccessGreen else QuizErrorRed
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (activeQuiz.isAnswerCorrect == true) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (activeQuiz.isAnswerCorrect == true) "Tebrikler, Doğru!" else "Maalesef Yanlış!",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "💡 Bilgi Notu:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = QuizGoldBright
                        )

                        Text(
                            text = question.explanation,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.95f),
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = { viewModel.nextQuestionOrFinish() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("quiz_next_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = QuizGold,
                                contentColor = QuizNavy
                            ),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text(
                                text = if (activeQuiz.currentQuestionIndex == 9) "Etabı Tamamla 🏆" else "Sonraki Soru ➡️",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Audience Poll Modal
    activeQuiz.audiencePoll?.let { poll ->
        AlertDialog(
            onDismissRequest = { viewModel.dismissAudiencePoll() },
            confirmButton = {
                TextButton(onClick = { viewModel.dismissAudiencePoll() }) {
                    Text("Kapat", color = QuizGold, fontWeight = FontWeight.Bold)
                }
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Poll, contentDescription = null, tint = QuizGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Seyirci Anketi Sonucu", color = Color.White, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val letters = listOf("A", "B", "C", "D")
                    poll.percentages.forEachIndexed { idx, pct ->
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${letters.getOrElse(idx) { "" }}) ${question.options.getOrElse(idx) { "" }}",
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    maxLines = 1,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "%$pct",
                                    fontWeight = FontWeight.Bold,
                                    color = QuizGold,
                                    fontSize = 13.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            LinearProgressIndicator(
                                progress = { pct / 100f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                color = if (idx == question.correctAnswerIndex) QuizSuccessGreen else QuizIndigoLight,
                                trackColor = QuizCardDark
                            )
                        }
                    }
                }
            },
            containerColor = QuizSurfaceDark,
            shape = RoundedCornerShape(20.dp)
        )
    }

    // Quit Dialog
    if (showQuitDialog) {
        AlertDialog(
            onDismissRequest = { showQuitDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        showQuitDialog = false
                        viewModel.navigateTo(ScreenState.STAGE_SELECT)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = QuizErrorRed)
                ) {
                    Text("Çıkış Yap")
                }
            },
            dismissButton = {
                TextButton(onClick = { showQuitDialog = false }) {
                    Text("Devam Et", color = Color.White)
                }
            },
            title = {
                Text("Etaptan Çıkılsın mı?", color = Color.White, fontWeight = FontWeight.Bold)
            },
            text = {
                Text(
                    "Şimdi çıkarsanız mevcut etabın ilerlemesi ve puanları kaydedilmeyecektir.",
                    color = QuizTextSecondary
                )
            },
            containerColor = QuizSurfaceDark,
            shape = RoundedCornerShape(20.dp)
        )
    }
}

@Composable
private fun LifelineButton(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isUsed: Boolean,
    isEnabled: Boolean,
    isActive: Boolean = false,
    onClick: () -> Unit,
    tag: String
) {
    Surface(
        modifier = Modifier
            .width(80.dp)
            .height(58.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable(enabled = isEnabled) { onClick() }
            .testTag(tag),
        shape = RoundedCornerShape(14.dp),
        color = when {
            isActive -> QuizGold.copy(alpha = 0.3f)
            isUsed -> QuizCardDark.copy(alpha = 0.3f)
            else -> QuizSurfaceDark
        },
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            when {
                isActive -> QuizGold
                isUsed -> QuizBorder.copy(alpha = 0.3f)
                else -> QuizIndigoLight.copy(alpha = 0.6f)
            }
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = when {
                    isActive -> QuizGold
                    isUsed -> QuizTextSecondary.copy(alpha = 0.4f)
                    else -> QuizGold
                },
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = when {
                    isUsed -> QuizTextSecondary.copy(alpha = 0.4f)
                    else -> Color.White
                }
            )
            Text(
                text = if (isUsed) "Kullanıldı" else subtitle,
                fontSize = 9.sp,
                color = when {
                    isUsed -> QuizTextSecondary.copy(alpha = 0.4f)
                    else -> QuizTextSecondary
                }
            )
        }
    }
}

@Composable
private fun QuizOptionCard(
    letter: String,
    text: String,
    isHidden: Boolean,
    isSelected: Boolean,
    isRevealed: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit,
    tag: String
) {
    if (isHidden) {
        // Discarded by 50:50 lifeline
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            color = QuizCardDark.copy(alpha = 0.15f),
            border = androidx.compose.foundation.BorderStroke(1.dp, QuizBorder.copy(alpha = 0.2f))
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "--- 50:50 Elendi ---",
                    style = MaterialTheme.typography.bodySmall,
                    color = QuizTextSecondary.copy(alpha = 0.4f)
                )
            }
        }
        return
    }

    val backgroundColor = when {
        isRevealed && isCorrect -> QuizSuccessGreen.copy(alpha = 0.3f)
        isRevealed && isSelected && !isCorrect -> QuizErrorRed.copy(alpha = 0.3f)
        isSelected -> QuizIndigo.copy(alpha = 0.3f)
        else -> QuizSurfaceDark
    }

    val borderColor = when {
        isRevealed && isCorrect -> QuizSuccessGreen
        isRevealed && isSelected && !isCorrect -> QuizErrorRed
        isSelected -> QuizGold
        else -> QuizBorder
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(enabled = !isRevealed) { onClick() }
            .testTag(tag),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Letter badge (A, B, C, D)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            isRevealed && isCorrect -> QuizSuccessGreen
                            isRevealed && isSelected && !isCorrect -> QuizErrorRed
                            isSelected -> QuizGold
                            else -> QuizCardDark
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = letter,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    color = when {
                        isRevealed && isCorrect -> Color.White
                        isRevealed && isSelected && !isCorrect -> Color.White
                        isSelected -> QuizNavy
                        else -> Color.White
                    }
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            if (isRevealed) {
                if (isCorrect) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Doğru",
                        tint = QuizSuccessGreen,
                        modifier = Modifier.size(24.dp)
                    )
                } else if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "Yanlış",
                        tint = QuizErrorRed,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
