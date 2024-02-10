package com.just.cse.digital_diary.two_zero_two_three.search.functionalities.event

 interface SearchFunctionalityEvent {
    data class CallRequest(val number: String) : SearchFunctionalityEvent
    data class MessageRequest(val number: String) : SearchFunctionalityEvent
    data class EmailRequest(val email: String) : SearchFunctionalityEvent
}