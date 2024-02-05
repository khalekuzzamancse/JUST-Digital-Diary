package com.just.cse.digital_diary.two_zero_two_three.auth.destination

import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginResponseModel

interface AuthDestinationEvent {
    data class LoginSuccess(val username:String,val password:String) : AuthDestinationEvent
    data object ExitRequest : AuthDestinationEvent
}