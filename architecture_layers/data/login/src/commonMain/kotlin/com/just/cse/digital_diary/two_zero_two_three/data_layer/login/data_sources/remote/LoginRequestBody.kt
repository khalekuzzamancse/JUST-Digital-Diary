package com.just.cse.digital_diary.two_zero_two_three.data_layer.login.data_sources.remote

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestBody(
    val usernameOrEmail: String ,
    val password: String
)