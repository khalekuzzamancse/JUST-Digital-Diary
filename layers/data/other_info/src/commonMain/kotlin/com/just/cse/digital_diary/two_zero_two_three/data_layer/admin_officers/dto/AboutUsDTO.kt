package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity.AboutUsModel

data class AboutUsDTO(
    val appName: String,
    val developedDepartmentName: String,
    val universityName: String,
    val otherInfo:String
) {
    fun toModel() = AboutUsModel(
        appName = appName,
        developedDepartmentName = developedDepartmentName,
        universityName = universityName,
        otherInfo=otherInfo
    )
}
