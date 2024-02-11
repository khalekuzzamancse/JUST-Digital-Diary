package com.just.cse.digitaldiary.twozerotwothree.core.di.auth

import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.data_sources.remote.RemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.repoisitory.RegisterRepositoryImpl
import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.RealmAuthentication
import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.authentication.responose_model.SignedInUserResponseModel

/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object AuthComponentProvider {


    var authToken: String? = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNzY2NzYyNiwiZXhwIjoxNzA3ODQwNDI2fQ.-y9SX4r6mnAbtPOdMi_qIrvk5g_7UFsf0634pVw_UN0"
    fun getLoginRepository(): LoginRepositoryImpl {
        return LoginRepositoryImpl()
    }

    fun getRegisterRepository(): RegisterRepositoryImpl {
        return RegisterRepositoryImpl()
    }


    suspend fun saveSignInInfo(
        username: String, password: String
    ) :Boolean{
        val response = RealmAuthentication.saveSignInInfo(
            SignedInUserResponseModel(username, password)
        )
       return response!=null

    }
     fun observeSignIn()=RealmAuthentication.observeSignIn()
    fun signInOut(){
      RealmAuthentication.signOut()
    }
    suspend fun updateAuthToken() {
        val response = RealmAuthentication.getSingedInUserInfo()
        if (response != null) {
            authToken = RemoteDataSource().requestToken(
                username = response.username, password = response.password
            )
        }
//        println("AuthComponentProvider:getAuthToken():$response")
//        println("AuthComponentProvider:getAuthToken():$authToken")
    }

}