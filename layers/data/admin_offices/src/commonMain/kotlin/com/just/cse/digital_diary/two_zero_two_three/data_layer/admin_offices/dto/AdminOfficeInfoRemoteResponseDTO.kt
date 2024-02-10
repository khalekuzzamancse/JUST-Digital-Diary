package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.model.AdminOfficeModel
import kotlinx.serialization.Serializable

@Serializable
data class AdminOfficeInfoRemoteResponseDTO(
    val id: Int,
    val ad_office_id: String,
    val name: String,
    val sub_offices_count: Int
){
    fun toModel()=AdminOfficeModel(
        id =ad_office_id,
        name=name,
        subOfficeCnt =sub_offices_count,
    )
}