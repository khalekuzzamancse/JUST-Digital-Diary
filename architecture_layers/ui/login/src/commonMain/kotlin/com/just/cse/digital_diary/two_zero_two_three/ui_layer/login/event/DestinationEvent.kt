package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event

sealed interface DestinationEvent {
    data object LoginRequest: DestinationEvent
    data object ExitRequest: DestinationEvent

}