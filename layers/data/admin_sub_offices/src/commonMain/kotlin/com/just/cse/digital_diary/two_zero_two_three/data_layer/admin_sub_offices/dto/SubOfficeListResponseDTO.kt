package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.dto

import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.dto.AdminSubOfficeDTO
import kotlinx.serialization.Serializable
//response JSON Format
@Serializable
data class SubOfficeListResponseDTO(
    val message: String,
    val data: List<AdminSubOfficeDTO>
)