package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form

data class RegisterFormEvent(
    val onNameChanged: (String) -> Unit,
    val onEmailChanged: (String) -> Unit,
    val onUserNameChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
    val onDeptChanged: (String) -> Unit,
    val onConfirmedPassword: (String) -> Unit,
)