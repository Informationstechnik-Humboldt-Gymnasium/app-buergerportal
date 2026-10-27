package de.humboldtgym.maiskolbistan.buergerportal.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = Gold400,
    onPrimary = Maroon900,
    primaryContainer = Parchment100,
    onPrimaryContainer = Maroon800,
    secondary = Maroon600,
    onSecondary = Parchment50,
    secondaryContainer = Maroon700,
    onSecondaryContainer = Parchment50,
    tertiary = Leaf500,
    background = Parchment50,
    onBackground = Ink900,
    surface = Parchment50,
    onSurface = Ink900,
    surfaceVariant = Parchment100,
    onSurfaceVariant = Ink800,
    outline = Parchment300
)

private val DarkColors = darkColorScheme(
    primary = Gold400,
    onPrimary = Maroon900,
    primaryContainer = Maroon800,
    onPrimaryContainer = Parchment100,
    secondary = Maroon600,
    onSecondary = Parchment50,
    tertiary = Leaf500,
    background = Maroon900,
    onBackground = Parchment50,
    surface = Maroon800,
    onSurface = Parchment50,
    surfaceVariant = Maroon700,
    onSurfaceVariant = Parchment100,
    outline = Ink700
)

@Composable
fun BuergerportalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
