package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = QuizGold,
    onPrimary = QuizNavy,
    primaryContainer = QuizIndigoDark,
    onPrimaryContainer = QuizGoldBright,
    secondary = QuizIndigoLight,
    onSecondary = Color.White,
    secondaryContainer = QuizIndigo,
    onSecondaryContainer = Color.White,
    tertiary = QuizAmber,
    onTertiary = Color.White,
    background = QuizNavy,
    onBackground = QuizTextPrimary,
    surface = QuizSurfaceDark,
    onSurface = QuizTextPrimary,
    surfaceVariant = QuizCardDark,
    onSurfaceVariant = QuizTextSecondary,
    outline = QuizBorder,
    error = QuizErrorRed,
    onError = Color.White
  )

private val LightColorScheme =
  lightColorScheme(
    primary = QuizLightPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0E7FF),
    onPrimaryContainer = QuizLightPrimary,
    secondary = QuizLightSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFEF3C7),
    onSecondaryContainer = QuizLightSecondary,
    tertiary = QuizAmber,
    onTertiary = Color.White,
    background = QuizLightBg,
    onBackground = QuizLightTextPrimary,
    surface = QuizLightSurface,
    onSurface = QuizLightTextPrimary,
    surfaceVariant = QuizLightCard,
    onSurfaceVariant = QuizLightTextSecondary,
    outline = Color(0xFFCBD5E1),
    error = QuizErrorRed,
    onError = Color.White
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = true, // Trivia game looks best in dark neon/midnight mode by default
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
