package common.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector


data class NavigationItemInfo<T>(
    val key:T,
    val label: String,
    val unFocusedIcon: ImageVector,
    val focusedIcon: ImageVector =unFocusedIcon,
    val route: String = label,
    val badge: String? = null,
)
data class NavigationItemInfo2<T>(
    val key:T,
    val label: String,
    val iconText: String,
    val route: String = label,
    val badge: String? = null,
)