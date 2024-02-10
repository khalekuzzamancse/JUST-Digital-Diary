package com.just.cse.digital_diary.two_zero_two_three.data_layer.register.repoisitory

import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.data_sources.remote.RegisterBody
import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.data_sources.remote.RemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterResponseModel
import com.just.cse.digital_diary.two_zero_two_three.domain.register.repository.RegisterRepository

class RegisterRepositoryImpl: RegisterRepository {
    override suspend fun register(model: RegisterRequestModel): RegisterResponseModel {
       val res= RemoteDataSource().register(
           body = RegisterBody(
               username =model.username,
               password = model.password,
               email=model.email,
               name=model.name
           )
       )
       return if(res.isSuccess&&res.result!=null){
            RegisterResponseModel.Success
        }
        else{
            RegisterResponseModel.Failure(reason = res.reason)
        }
    }

}