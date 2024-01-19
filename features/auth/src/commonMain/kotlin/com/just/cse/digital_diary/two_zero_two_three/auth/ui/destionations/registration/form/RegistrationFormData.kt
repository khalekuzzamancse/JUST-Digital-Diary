package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form

data class RegistrationFormData(
    val name: String,
    val email: String,
    val username: String,
    val dept:String,
    val password: String,
    val confirmPassword: String
)