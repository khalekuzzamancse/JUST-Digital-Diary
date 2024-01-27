package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.events

interface RegisterDestinationEvent {

    data object ExitRequest : RegisterDestinationEvent
    data object RegisterSuccess : RegisterDestinationEvent
    data class NewMessage(val message: String?):RegisterDestinationEvent
    data object LoadingStart:RegisterDestinationEvent
    data object LoadingFinish:RegisterDestinationEvent


}