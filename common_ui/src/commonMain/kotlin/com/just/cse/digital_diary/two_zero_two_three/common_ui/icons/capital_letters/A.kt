package com.just.cse.digital_diary.two_zero_two_three.common_ui.icons.capital_letters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.icons.line
import com.just.cse.digital_diary.two_zero_two_three.common_ui.icons.move

@Composable
fun A(
    modifier: Modifier=Modifier,
    color: Color=Color.Red,
    strokeWidth: Float=2f,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = this.size.width
        val height = this.size.height
        val centerTop = Offset(width, 0f) / 2f
        val leftBottom = Offset(0f, height)
        val rightBottom = Offset(width, height)

        drawPath(
            color = color,
            path = Path().apply {
                move(leftBottom)
                line(start = leftBottom, end = centerTop)
                line(start = centerTop, end = rightBottom)

            },
            style = Stroke(strokeWidth)
        )

    }

}