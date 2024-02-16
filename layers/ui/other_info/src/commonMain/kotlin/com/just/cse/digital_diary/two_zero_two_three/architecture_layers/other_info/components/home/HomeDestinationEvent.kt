package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home

interface HomeDestinationEvent {
    data class CalenderViewRequest(val url:String) : HomeDestinationEvent
    data object NavigationRequest : HomeDestinationEvent

}