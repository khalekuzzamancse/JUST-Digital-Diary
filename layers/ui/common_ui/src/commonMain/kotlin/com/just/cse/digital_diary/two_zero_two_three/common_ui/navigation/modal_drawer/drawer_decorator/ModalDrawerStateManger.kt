package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.drawer_decorator

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.NavItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state.ModalDrawerSheetState
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state.NavGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * * The factory to create easily the [ModelDrawerState],so that client code
 * is short and clean enough
 * * Needed to refactor for coroutine scope
 */

class  ModalDrawerStateManger   (
    group: List<NavGroup>,
    itemVisibilityDelay: Long? = null,
    animateAbleSheet: Boolean = true
) {
    private val _drawerState = MutableStateFlow(
        ModelDrawerState(
            sheetState = ModalDrawerSheetState(
                groups = group,
                itemVisibilityDelay = itemVisibilityDelay,
            ),
            drawerState = DrawerState(DrawerValue.Closed),
            hasAnimation = animateAbleSheet
        )
    )
    val drawerState = _drawerState.asStateFlow()



    /**
     * * No Default params
     * * It a factory method that make the object creation easy
     */
    /**
     * Refactored need later
     */
    fun openDrawer() {
        _drawerState.update {
            it.copy(
                drawerState = DrawerState(DrawerValue.Open) //do not create new instance of DrawerState
                //use drawerState.open, fix the coroutine issue first,then do it
            )
        }
    }

    fun makeSelection(id: String) {
        val newSheetState = drawerState.value.sheetState.copy(selected = id)
        _drawerState.update {
            it.copy(
                sheetState = newSheetState,
                drawerState = DrawerState(DrawerValue.Closed)
            )
        }
//
//            drawerState.value.drawerState.close()
        //not replace the state every time close,because this would create new
        //Drawer State object every time,which might by expensive
        //but for some reason desktop coroutine is causes crashes,that is why we are direcly
        //creating new state,refactor it later

    }


}