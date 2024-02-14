package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state

import androidx.compose.runtime.Immutable
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.NavItem


/**
 * @param selected is the id [NavItem] of that item.it is nullable because it possible that user does selected any item yet.
 */
@Immutable
data class ModalDrawerSheetState(
    val groups: List<NavGroup>,
    val selected: String? = null,
    val itemVisibilityDelay: Long? = null
)
