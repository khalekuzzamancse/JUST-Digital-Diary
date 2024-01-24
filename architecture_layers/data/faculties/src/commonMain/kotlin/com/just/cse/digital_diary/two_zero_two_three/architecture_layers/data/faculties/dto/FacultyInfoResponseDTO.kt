package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.model.FacultyInfoModel
import kotlinx.serialization.Serializable

@Serializable
data class FacultyInfoResponseDTO(
    val id: Int,
    val faculty_id: String,
    val name: String,
    val departmentsCount: Int
){
    fun toModel()=FacultyInfoModel(
        facultyId =faculty_id,
        name=name,
        departmentsCount=departmentsCount,
        id=id,
    )
}