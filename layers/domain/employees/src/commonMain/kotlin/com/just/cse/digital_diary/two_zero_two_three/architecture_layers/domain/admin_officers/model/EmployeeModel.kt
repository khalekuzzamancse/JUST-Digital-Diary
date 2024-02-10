package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model


data class EmployeeModel(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String="https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
    val achievements: String,
    val phone: String,
    val designations: String,
    val roomNo: String,
)