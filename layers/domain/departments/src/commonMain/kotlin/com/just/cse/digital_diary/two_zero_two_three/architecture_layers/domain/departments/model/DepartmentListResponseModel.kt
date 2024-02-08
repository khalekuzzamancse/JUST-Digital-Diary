package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.model

interface DepartmentListResponseModel {
    data class Success(val data: List<DepartmentListModel>):DepartmentListResponseModel
    data class Failure(val reason: String?):DepartmentListResponseModel
}