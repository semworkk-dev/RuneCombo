package com.runacombo.ui.theme

import androidx.compose.foundation.isSystemInDarkMode
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Background,
    primaryContainer = PrimaryDark,
    onPrimaryContainer = Primary,
    secondary = Accent,
    onSecondary = Background,
    secondaryContainer = Surface,
    onSecondaryContainer = Accent,
    tertiary = Success,
    onTertiary = Background,
    tertiaryContainer = Surface,
    onTertiaryContainer = Success,
    error = Error,
    onError = Background,
    errorContainer = Surface,
    onErrorContainer = Error,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = PrimaryDark,
    onSurfaceVariant = TextSecondary,
    outline = Accent,
    outlineVariant = Surface,
    scrim = Background
)

@Composable
fun RunaComboTheme(
    darkTheme: Boolean = isSystemInDarkMode(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
