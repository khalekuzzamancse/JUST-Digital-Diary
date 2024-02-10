package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.state

data class RegistrationFormData(
    val name: String,
    val email: String,
    val username: String,
    val dept:String,
    val password: String,
    val confirmPassword: String
)