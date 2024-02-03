package com.just.cse.digital_diary.two_zero_two_three.auth.destination

interface AuthDestinationEvent {
    data object LoginSuccess:AuthDestinationEvent
    data object ExitRequest:AuthDestinationEvent
}