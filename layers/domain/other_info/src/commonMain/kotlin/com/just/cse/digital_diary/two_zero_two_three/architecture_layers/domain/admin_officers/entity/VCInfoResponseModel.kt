package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity

interface VCInfoResponseModel {
    data class Success(val data: VCInfoModel): VCInfoResponseModel
    data class Failure(val reason: String?): VCInfoResponseModel
}