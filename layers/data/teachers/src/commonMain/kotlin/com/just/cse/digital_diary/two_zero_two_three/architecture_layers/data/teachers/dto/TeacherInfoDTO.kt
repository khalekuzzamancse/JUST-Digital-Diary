package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.model.TeacherModel
import kotlinx.serialization.Serializable

@Serializable
data class TeacherInfoDTO (
    val uid: String,
    val name: String,
    val email: String,
    val additional_email: String?,
    val profile_image: String?,
    val achievement: String,
    val phone: String?,
    val designations: String,
    val department_name: String,
    val department_shortname: String,
    val room_no: String
){
    fun toModel()= TeacherModel(
        name=name,
        email=email,
        additionalEmail = additional_email?:"",
        profileImageLink = profile_image?:"",
        achievements=achievement,
        phone=phone?:"",
        designations=designations,
        deptName = department_name,
        deptSortName=department_shortname,
        roomNo = room_no

    )

}
