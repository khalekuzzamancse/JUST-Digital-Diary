package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote.dto

import kotlinx.serialization.Serializable
//same as the api JSON
@Serializable
class FacultyListResponseDTO(
    val message:String?=null,
    val data: List<FacultyInfoResponseDTO>
)