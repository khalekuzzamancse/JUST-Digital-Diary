package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.dto

import kotlinx.serialization.Serializable

@Serializable
data class TeacherListDTO(
    val message: String,
    val data: List<TeacherInfoDTO>
)
