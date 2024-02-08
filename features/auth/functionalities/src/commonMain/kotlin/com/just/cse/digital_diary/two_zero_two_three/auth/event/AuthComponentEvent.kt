package com.just.cse.digital_diary.two_zero_two_three.auth.event

interface AuthComponentEvent {
    data object LoginSuccess:AuthComponentEvent
    data object ExitRequest:AuthComponentEvent
}