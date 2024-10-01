package common.ui.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class NavigationItemProps(
    val focusedColor: Color,
    val unFocusedColor: Color,
    val iconLabelColor:Color= Color.Unspecified,
    val iconTint:Color= Color.Unspecified,
    val shape: Shape = RoundedCornerShape(8.dp),
)