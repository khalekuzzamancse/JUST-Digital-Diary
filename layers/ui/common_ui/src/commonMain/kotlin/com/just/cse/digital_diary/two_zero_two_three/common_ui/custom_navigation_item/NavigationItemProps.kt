package com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class NavigationItemProps(
    val focusedColor: Color,
    val unFocusedColor: Color,
    val shape: Shape = RoundedCornerShape(8.dp)
)