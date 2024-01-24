package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model

data class AdminOfficerModel(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImage: String,
    val achievement: String,
    val phone: String?,
    val designations: String,
    val roomNo: String
)