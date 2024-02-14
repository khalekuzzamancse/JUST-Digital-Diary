package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.event

/**
 * * Event for the drawer sheet.
 * * Defining so that number of parameter  is reduced to sheet decorator
 * * Also it easy to add or remove new event easily,and propagate up to the client
 */
sealed interface DrawerSheetEvent {
    data class Selected(val id: String) : DrawerSheetEvent
    data class Hovered(val id: String) : DrawerSheetEvent
}