package com.just.cse.digital_diary.two_zero_two_three.auth.state

data class AuthScreenState(
    val showProgressBar: Boolean=false,
    val snackBarMessage: String?=null,
    val showRegisterForm: Boolean=false
)
