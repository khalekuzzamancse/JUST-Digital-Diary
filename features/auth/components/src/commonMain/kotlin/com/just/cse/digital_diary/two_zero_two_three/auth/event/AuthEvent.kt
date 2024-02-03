package com.just.cse.digital_diary.two_zero_two_three.auth.event

interface AuthEvent {
    data object LoginSuccess:AuthEvent
    data object ExitRequest:AuthEvent
}