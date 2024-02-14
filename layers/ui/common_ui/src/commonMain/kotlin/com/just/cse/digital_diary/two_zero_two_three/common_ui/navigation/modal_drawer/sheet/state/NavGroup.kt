package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state

import androidx.compose.runtime.Immutable
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.NavItem

/**
 * @param label is nullable because there are some use cases when label is not needed,only divider is enough
 */
@Immutable
data class NavGroup(
    val label:String?=null,
    val items:List<NavItem>
)
