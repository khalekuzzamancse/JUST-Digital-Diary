package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.model

interface AdminOfficeListResponseModel {
    data class Success(val data: List<AdminOfficeModel>): AdminOfficeListResponseModel
    data class Failure(val reason: String?): AdminOfficeListResponseModel
}