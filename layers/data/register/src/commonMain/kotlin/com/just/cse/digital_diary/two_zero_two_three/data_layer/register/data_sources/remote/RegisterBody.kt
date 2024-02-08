package com.just.cse.digital_diary.two_zero_two_three.data_layer.register.data_sources.remote

import kotlinx.serialization.Serializable

@Serializable
data class RegisterBody(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)