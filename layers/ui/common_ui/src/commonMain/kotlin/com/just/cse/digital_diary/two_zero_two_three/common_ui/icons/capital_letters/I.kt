package com.just.cse.digital_diary.two_zero_two_three.common_ui.icons.capital_letters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import com.just.cse.digital_diary.two_zero_two_three.common_ui.icons.line
import com.just.cse.digital_diary.two_zero_two_three.common_ui.icons.move

@Composable
fun I(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    strokeWidth: Float=2f,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = this.size.width
        val height = this.size.height
        val topCenter = Offset(width/2, 0f)
        val bottomCenter = Offset(width/2, height)
        drawPath(
            color = color,
            path = Path().apply {
                move(bottomCenter)
                line(start = topCenter, end = bottomCenter)
                line(start = topCenter- Offset(10f,0f), end = topCenter+ Offset(10f,0f))
                line(start = bottomCenter-Offset(10f,0f), end = bottomCenter+Offset(10f,0f))
            },
            style = Stroke(strokeWidth)
        )

    }

}