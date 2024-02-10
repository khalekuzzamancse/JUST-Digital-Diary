package com.just.cse.digital_diary.two_zero_two_three.domain.register.model

interface RegisterResponseModel {
    data object Success : RegisterResponseModel
    data class Failure(val reason: String?) : RegisterResponseModel

}