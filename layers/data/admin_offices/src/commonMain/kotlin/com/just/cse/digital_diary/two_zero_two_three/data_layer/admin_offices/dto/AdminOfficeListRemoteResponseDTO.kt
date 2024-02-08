package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.dto

import kotlinx.serialization.Serializable
//same as the api JSON
@Serializable
class AdminOfficeListRemoteResponseDTO(
    val message:String?,
    val data: List<AdminOfficeInfoRemoteResponseDTO>
)