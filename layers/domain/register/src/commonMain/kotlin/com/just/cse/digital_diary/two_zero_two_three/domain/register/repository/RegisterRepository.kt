package com.just.cse.digital_diary.two_zero_two_three.domain.register.repository

import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterResponseModel


interface RegisterRepository {
    suspend fun register(model: RegisterRequestModel): RegisterResponseModel
}

