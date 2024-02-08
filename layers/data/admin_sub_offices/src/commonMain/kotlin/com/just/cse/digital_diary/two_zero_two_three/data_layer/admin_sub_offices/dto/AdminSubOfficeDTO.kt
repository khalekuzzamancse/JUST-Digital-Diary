package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.model.AdminSubOfficeModel
import kotlinx.serialization.Serializable

//response JSON Format
@Serializable
data class AdminSubOfficeDTO(
    val id: Int,
    val sub_office_id: String,
    val name: String,
    val members_count: Int
){
    fun toModel()= AdminSubOfficeModel(
        name = name,
        id = sub_office_id,
        employeeCount = members_count,
    )
}