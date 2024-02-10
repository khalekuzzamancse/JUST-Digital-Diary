package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery.state

data class EventGalleryState(
    val events: List<Event> = emptyList(),
    val isLoading:Boolean=false
)
