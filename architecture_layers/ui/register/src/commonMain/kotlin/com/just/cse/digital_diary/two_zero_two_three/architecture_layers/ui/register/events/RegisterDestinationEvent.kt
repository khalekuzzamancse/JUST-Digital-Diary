package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.events

interface RegisterDestinationEvent {

    data object ExitRequest : RegisterDestinationEvent
    interface RegisterControlEvents : RegisterDestinationEvent {
        data object RegisterRequest : RegisterControlEvents

    }


}