package com.just.cse.digital_diary.two_zero_two_three.data_layer.register.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDTO(
    val message: String,
    val userId: Int
)