package com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model

interface LoginResponseModel {
    data class Success(val token: String) : LoginResponseModel
    data class Failure(val reason: String?) : LoginResponseModel

}