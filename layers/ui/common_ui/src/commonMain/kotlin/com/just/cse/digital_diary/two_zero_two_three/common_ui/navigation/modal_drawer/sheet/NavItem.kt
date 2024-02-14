package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * @param label the label that will be shown in the drawer sheet,should be as short as possible.
 * @param id  Uniquely identifies the item. This is especially useful when dealing with groups in the drawer.
 * @param selectedIcon
 * @param unselectedIcon
 * When working with groups, using an index for unique identification is not optimal due to multiple lists within the group,
 * making the code less understandable. Hence, using the provided id for clarity.
 * Since drawer labels are unique, they can be used as identifiers.
 * * Mandatory parameters : [label],[unselectedIcon]
 * * It can be used with NavRail,Modal Drawer and BottomBar
 * * It should not include unnecessary information, such as route details.

 */
@Immutable
data class NavItem(
    val label:String,
    val unselectedIcon: ImageVector,
    val id:String=label,
    val selectedIcon: ImageVector = unselectedIcon,
)
