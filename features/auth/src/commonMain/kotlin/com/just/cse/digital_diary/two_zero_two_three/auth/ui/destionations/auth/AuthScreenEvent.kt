package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.auth

data class AuthScreenEvent(
    val onRegistrationRequest: () -> Unit,
    val onRegistrationFromOpenRequest: () -> Unit,
    val onRegistrationFromCloseRequest: () -> Unit,
    val onLoginRequest: () -> Unit,
)