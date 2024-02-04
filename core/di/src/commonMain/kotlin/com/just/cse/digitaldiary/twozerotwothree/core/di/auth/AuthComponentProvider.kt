package com.just.cse.digitaldiary.twozerotwothree.core.di.auth

import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.data_sources.remote.RemoteDataSource
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.repoisitory.RegisterRepositoryImpl
import com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm.RealmAuthentication

/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object AuthComponentProvider {

    var authToken: String? = null
    fun getLoginRepository(): LoginRepositoryImpl {
        return LoginRepositoryImpl()
    }

    fun getRegisterRepository(): RegisterRepositoryImpl {
        return RegisterRepositoryImpl()
    }

    suspend fun updateAuthToken() {
        val response = RealmAuthentication.getSingedInUserInfo()
        if (response != null) {
//           authToken= RemoteDataSource().requestToken(
//                username = "khalek02", password = "test@123"
//            )
            authToken= RemoteDataSource().requestToken(
                username = response.username, password = response.password
            )
        }
        println("AuthComponentProvider:getAuthToken():$response")
        println("AuthComponentProvider:getAuthToken():$authToken")
    }

}