package com.just.cse.digital_diary.two_zero_two_three.common_ui.icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

fun Path.move(offset: Offset) {
    moveTo(offset.x, offset.y)
}

fun Path.line(start: Offset, end: Offset) {
    move(start)
    lineTo(end.x, end.y)
}