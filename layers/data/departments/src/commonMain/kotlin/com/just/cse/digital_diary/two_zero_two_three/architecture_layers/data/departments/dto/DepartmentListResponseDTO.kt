package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.dto

import kotlinx.serialization.Serializable
//response JSON Format
@Serializable
data class DepartmentListResponseDTO(
    val message: String,
    val data: List<DepartmentInfoDTO>
)