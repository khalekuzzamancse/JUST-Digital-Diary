package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.model

interface TeacherListResponseModel {
    data class Success(val data: List<TeacherModel>):TeacherListResponseModel
    data class Failure(val reason: String?):TeacherListResponseModel
}