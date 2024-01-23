package com.just.cse.digital_diary.two_zero_two_three.data_layer.login.model

interface LoginResponse {
    data class Success(val response:LoginResult):LoginResponse
    object InternetNotConnected:LoginResponse
    object WrongInput:LoginResponse
}