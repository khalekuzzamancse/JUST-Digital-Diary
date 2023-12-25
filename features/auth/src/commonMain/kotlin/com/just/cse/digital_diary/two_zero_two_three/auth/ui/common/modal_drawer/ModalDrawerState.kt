package com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ModalDrawerState(
    private val scope: CoroutineScope,
) {
    val drawerState = DrawerState(DrawerValue.Open)
    fun openDrawer() {
        scope.launch {
            drawerState.open()
        }
    }

    fun closeDrawer() {
        scope.launch {
            drawerState.close()
        }
    }
}
