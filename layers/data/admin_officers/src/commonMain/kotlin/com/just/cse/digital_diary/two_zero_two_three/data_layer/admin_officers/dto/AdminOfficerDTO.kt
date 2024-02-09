package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity.AdminOfficerModel
import kotlinx.serialization.Serializable

@Serializable
data class AdminOfficerDTO (
    val uid: String,
    val name: String,
    val email: String,
    val additional_email: String?,
    val profile_image: String?,
    val achievement: String,
    val phone: String?,
    val designations: String,
    val room_no: String
){
    fun toModel()= AdminOfficerModel(
        name=name,
        email=email,
        additionalEmail = additional_email?:"",
        profileImage= profile_image?:"",
        achievements=achievement,
        phone=phone?:"",
        designations=designations,
        roomNo = room_no

    )

}
