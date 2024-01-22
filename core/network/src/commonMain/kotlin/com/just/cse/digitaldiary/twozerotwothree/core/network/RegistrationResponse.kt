package com.just.cse.digitaldiary.twozerotwothree.core.network

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val message: String,
    val userId: Int
)