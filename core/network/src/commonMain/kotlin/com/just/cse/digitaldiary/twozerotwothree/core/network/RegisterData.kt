package com.just.cse.digitaldiary.twozerotwothree.core.network

import kotlinx.serialization.Serializable

@Serializable
data class RegisterData(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)