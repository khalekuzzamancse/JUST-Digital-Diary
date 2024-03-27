package auth.di

import auth.data.login.data_sources.remote.RemoteDataSource
import auth.data.login.repository.LoginRepositoryImpl
import auth.data.register.repoisitory.RegisterRepositoryImpl
import database.local.api.AuthAPIs
import database.local.schema.SignedInUserEntityLocal


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object AuthComponentProvider {

    var authToken: String?=null

    val isSingedIn= AuthAPIs.signInFlow
    fun getLoginRepository(): LoginRepositoryImpl {
        return LoginRepositoryImpl()
    }

    fun getRegisterRepository(): RegisterRepositoryImpl {
        return RegisterRepositoryImpl()
    }


    suspend fun saveSignInInfo(
        username: String, password: String
    ): Boolean {
        val response = AuthAPIs.saveSignInInfo(
            SignedInUserEntityLocal(username, password)
        )

        return response != null

    }

    fun observeSignIn() = AuthAPIs.observeSignIn()
    fun signInOut() {
        AuthAPIs.signOut()
    }

    suspend fun updateAuthToken() {
        val response = AuthAPIs.getSingedInUserInfo()
        if (response != null) {
            authToken = RemoteDataSource().requestToken(
                username = response.username, password = response.password
            )
        }
//        println("AuthComponentProvider:getAuthToken():$response")
//        println("AuthComponentProvider:getAuthToken():$authToken")
    }

}