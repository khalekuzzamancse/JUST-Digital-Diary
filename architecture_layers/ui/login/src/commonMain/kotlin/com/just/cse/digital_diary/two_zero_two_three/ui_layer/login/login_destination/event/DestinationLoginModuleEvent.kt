package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.event

interface DestinationLoginModuleEvent:LoginModuleEvent {
    data object ExitRequest : DestinationLoginModuleEvent
}