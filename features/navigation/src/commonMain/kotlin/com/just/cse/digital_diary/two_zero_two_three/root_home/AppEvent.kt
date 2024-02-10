package com.just.cse.digital_diary.two_zero_two_three.root_home

interface AppEvent {
    data class CallRequest(val number: String) : AppEvent
    data class MessageRequest(val number: String) : AppEvent
    data class EmailRequest(val email: String) : AppEvent
    data class WebVisitRequest(val url: String) : AppEvent
}
