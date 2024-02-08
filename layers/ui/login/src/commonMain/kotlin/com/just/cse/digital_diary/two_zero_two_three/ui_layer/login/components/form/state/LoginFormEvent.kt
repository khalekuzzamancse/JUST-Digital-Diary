package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.state

data class LoginFormEvent(
    val onUserNameChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
)
