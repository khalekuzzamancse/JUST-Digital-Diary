package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.event

data class RegisterFormEvent(
    val onNameChanged: (String) -> Unit,
    val onEmailChanged: (String) -> Unit,
    val onUserNameChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
    val onDeptChanged: (String) -> Unit,
    val onConfirmedPassword: (String) -> Unit,
)