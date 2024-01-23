package com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.model.LoginRequest
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.model.LoginResponse

interface LoginRepository {
    suspend fun login(model: LoginRequest):LoginResponse
}

