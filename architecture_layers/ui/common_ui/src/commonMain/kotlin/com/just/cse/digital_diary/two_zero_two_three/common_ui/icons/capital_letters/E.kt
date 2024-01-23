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
fun E(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    strokeWidth: Float=2f,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = this.size.width
        val height = this.size.height
        val topLeft = Offset(0f, 0f)
        val topRight = Offset(width, 0f)
        val bottomLeft = Offset(0f, height)
        val bottomRight = Offset(width, height)
        val centerLeft=Offset(0f,height/2)
        val centerRight = Offset(width, height/2)

        drawPath(
            color = color,
            path = Path().apply {
                move(topRight)
                line(start = topLeft, end = topRight)
                line(start = topLeft, end = bottomLeft)
                line(start = bottomLeft, end = bottomRight)
                line(start = centerLeft, end =centerRight)
            },
            style = Stroke(strokeWidth)
        )

    }

}