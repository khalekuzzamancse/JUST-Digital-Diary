package common.ui

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

fun Modifier.gradientBackground(colors: List<Color>): Modifier {
    return this.background(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        )
    )
}

/**
 * Determines a contrasting content color based on the background colors.
 */
fun getContrastingContentColor(backgroundColors: List<Color>): Color {
    val averageLuminance = backgroundColors.map { it.luminance() }.average()
    return if (averageLuminance > 0.5) Color.Black else Color.White
}