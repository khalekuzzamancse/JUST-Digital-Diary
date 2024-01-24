package com.just.cse.digital_diary.two_zero_two_three.data_layer.login.dto

import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginResponseModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginResultModelModel
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResponseDTO(
    val message: String,
    val token: String
){
    fun toModel()=LoginResponseModel.Success(token = token)
}