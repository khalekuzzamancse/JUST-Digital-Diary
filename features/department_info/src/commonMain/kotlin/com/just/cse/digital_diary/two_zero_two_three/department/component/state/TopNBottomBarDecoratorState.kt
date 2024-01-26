package com.just.cse.digital_diary.two_zero_two_three.department.component.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo
import com.just.cse.digital_diary.two_zero_two_three.department.component.bottomNavigationItems

data class TopNBottomBarDecoratorState(
    val topBarTitle: String = "Home",
    val topNavigationIcon: ImageVector? = Icons.Default.Menu,
    val bottomDestinations: List<NavigationItemInfo<Int>> = bottomNavigationItems,
    val selectedDestination: Int = 0

)
