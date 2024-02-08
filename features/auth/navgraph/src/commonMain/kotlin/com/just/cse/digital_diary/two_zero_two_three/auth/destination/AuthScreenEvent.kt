package com.just.cse.digital_diary.two_zero_two_three.auth.destination

interface AuthScreenEvent {
    data class LoginSuccess(val username:String,val password:String) : AuthScreenEvent
    data object ExitRequest : AuthScreenEvent
}