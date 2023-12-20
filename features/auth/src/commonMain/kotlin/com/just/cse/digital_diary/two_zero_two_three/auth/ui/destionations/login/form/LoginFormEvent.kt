package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form

data class LoginFormEvent(
    val onUserNameChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
)
