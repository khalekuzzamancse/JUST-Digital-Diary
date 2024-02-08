package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto

import kotlinx.serialization.Serializable

@Serializable
data class AdminOfficerListDTO(
    val message: String,
    val data: List<AdminOfficerDTO>
)
