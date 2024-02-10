package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity.VCInfoModel

data class VCInfoDTO(
    val name: String,
    val details: String,
    val message: String,
    val imageUrl: String
){
    fun toModel()= VCInfoModel(
         name=name,
         details=details,
        message=message,
        imageUrl=imageUrl
    )
}
