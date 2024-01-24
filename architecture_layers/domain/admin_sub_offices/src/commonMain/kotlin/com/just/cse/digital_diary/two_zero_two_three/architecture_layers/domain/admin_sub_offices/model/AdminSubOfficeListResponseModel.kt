package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.model

interface AdminSubOfficeListResponseModel {
    data class Success(val data: List<AdminSubOfficeModel>): AdminSubOfficeListResponseModel
    data class Failure(val reason: String?): AdminSubOfficeListResponseModel
}