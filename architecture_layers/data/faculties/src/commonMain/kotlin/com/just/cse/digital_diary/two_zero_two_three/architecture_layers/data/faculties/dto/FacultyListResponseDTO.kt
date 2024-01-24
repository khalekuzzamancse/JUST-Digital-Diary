package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.dto

import kotlinx.serialization.Serializable
//same as the api JSON
@Serializable
class FacultyListResponseDTO(
    val message:String?,
    val data: List<FacultyInfoResponseDTO>
)