package com.just.cse.digital_diary.two_zero_two_three.domain.register.model

data class RegisterRequestModel(
    val name: String,
    val email: String,
    val username:String,
    val password:String
)
