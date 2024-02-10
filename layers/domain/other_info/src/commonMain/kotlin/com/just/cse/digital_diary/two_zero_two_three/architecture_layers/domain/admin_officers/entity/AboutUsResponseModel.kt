package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity

interface AboutUsResponseModel {
    data class Success(val data: AboutUsModel): AboutUsResponseModel
    data class Failure(val reason: String?): AboutUsResponseModel
}