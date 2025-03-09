package com.nguyenmoclam.androidessentialsguide.compose

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun StudyGuideTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Check if dynamic color is available
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors =
        when {
            dynamicColor && isDarkTheme -> dynamicDarkColorScheme(LocalContext.current)
            dynamicColor && !isDarkTheme -> dynamicLightColorScheme(LocalContext.current)
            isDarkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content,
    )
}

private val LightColorScheme =
    lightColorScheme(
        primary = Color(0xFF1976D2),
        onPrimary = Color.White,
        secondary = Color(0xFF03A9F4),
        onSecondary = Color.Black,
        background = Color(0xFFF5F5F5),
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Color.Black,
        tertiary = Color(0xFF00BCD4),
        onTertiary = Color.Black,
    )

private val DarkColorScheme =
    darkColorScheme(
        primary = Color(0xFF90CAF9),
        onPrimary = Color.Black,
        secondary = Color(0xFF81D4FA),
        onSecondary = Color.Black,
        background = Color(0xFF121212),
        onBackground = Color.White,
        surface = Color(0xFF1E1E1E),
        onSurface = Color.White,
        tertiary = Color(0xFF80DEEA),
        onTertiary = Color.Black,
    )

val AppTypography = Typography()
val AppShapes = Shapes()
