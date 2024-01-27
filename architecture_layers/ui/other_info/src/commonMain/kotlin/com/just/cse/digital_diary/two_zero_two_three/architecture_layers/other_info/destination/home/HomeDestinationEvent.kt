package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.home

interface HomeDestinationEvent {
    data object NoteCreateRequest : HomeDestinationEvent
    data object NavigationRequest : HomeDestinationEvent

}