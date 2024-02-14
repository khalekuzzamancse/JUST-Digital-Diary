package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.drawer_decorator

import androidx.compose.material3.DrawerState
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state.ModalDrawerSheetState

/**
 * [drawerState] is needed to make the drawer open or close.it single copy need to maintain
 */
data class ModelDrawerState(
    val sheetState: ModalDrawerSheetState,
    val drawerState:DrawerState,
    val hasAnimation:Boolean=true,
)
