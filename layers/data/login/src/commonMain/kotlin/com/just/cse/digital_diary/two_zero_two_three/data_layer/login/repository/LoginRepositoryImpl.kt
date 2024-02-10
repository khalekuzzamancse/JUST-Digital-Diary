package com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository

import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.data_sources.remote.RemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginResponseModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory.LoginRepository

class LoginRepositoryImpl: LoginRepository {
    override suspend fun login(model: LoginRequestModel): LoginResponseModel {
        val result=RemoteDataSource().login(username = model.username, password = model.password)
        return if(result.result!=null){
           result.result.toModel()
        } else{
            LoginResponseModel.Failure(reason = result.reason)
        }
    }

}