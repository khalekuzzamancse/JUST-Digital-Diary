package com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginResponseModel

interface LoginRepository {
    suspend fun login(model: LoginRequestModel): LoginResponseModel
}

