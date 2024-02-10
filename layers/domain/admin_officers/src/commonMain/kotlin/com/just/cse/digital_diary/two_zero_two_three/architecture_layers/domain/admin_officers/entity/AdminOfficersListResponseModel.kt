package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity

interface AdminOfficersListResponseModel {
    data class Success(val data: List<AdminOfficerModel>): AdminOfficersListResponseModel
    data class Failure(val reason: String?): AdminOfficersListResponseModel
}