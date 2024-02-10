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
fun W(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    strokeWidth: Float=2f,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = this.size.width
        val height = this.size.height
        val topLeft = Offset(0f, 0f)
        val topRight = Offset(width, 0f)
        val bottomLeft = Offset(10f, height)
        val center = Offset(width/2,height/2)
        val bottomRight = Offset(width-10f, height)

        drawPath(
            color = color,
            path = Path().apply {
                move(topRight)
                line(start = topLeft, end = bottomLeft)
                line(start = topRight, end = bottomRight)
                line(start = bottomLeft, end =center)
                line(start = bottomRight, end =center)

            },
            style = Stroke(strokeWidth)
        )

    }

}