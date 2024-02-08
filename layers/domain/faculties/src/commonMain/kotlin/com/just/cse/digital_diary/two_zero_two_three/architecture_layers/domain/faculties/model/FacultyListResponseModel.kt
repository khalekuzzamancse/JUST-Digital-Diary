package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.model

interface FacultyListResponseModel {
    data class Success(val data: List<FacultyInfoModel>):FacultyListResponseModel
    data class Failure(val reason: String?):FacultyListResponseModel
}