package com.wjf.androidutils.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = DarkGray,
    onBackground = Color.White,
    surface = LightBlue,
    onSurface = DarkGray
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    background = RedOrange,
    onBackground = Color.White,
    surface = LightGreen,
    onSurface = LightGreen
)

@Composable
fun MyAppTheme(darkTheme: Boolean = false, content: @Composable() () -> Unit) {
    val fontScale = LocalDensity.current.fontScale
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val widthPixels = displayMetrics.widthPixels

    MaterialTheme(
        colors = if (darkTheme) LightColorPalette else DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = {
            CompositionLocalProvider(
                LocalDensity provides Density(
                    density = widthPixels / 1080.0f,
                    fontScale = fontScale
                )
            ) {
                content()
            }
        }
    )
}
