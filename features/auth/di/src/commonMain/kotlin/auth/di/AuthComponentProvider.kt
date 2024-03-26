package auth.di

import auth.data.login.data_sources.remote.RemoteDataSource
import auth.data.login.repository.LoginRepositoryImpl
import auth.data.register.repoisitory.RegisterRepositoryImpl
import core.database.realm.auth.RealmAuthentication
import core.database.realm.auth.SignedInUserResponseModel

/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object AuthComponentProvider {

    var authToken: String?=null

    val isSingedIn= RealmAuthentication.signInFlow
    fun getLoginRepository(): LoginRepositoryImpl {
        return LoginRepositoryImpl()
    }

    fun getRegisterRepository(): RegisterRepositoryImpl {
        return RegisterRepositoryImpl()
    }


    suspend fun saveSignInInfo(
        username: String, password: String
    ): Boolean {
        val response = RealmAuthentication.saveSignInInfo(
            SignedInUserResponseModel(username, password)
        )

        return response != null

    }

    fun observeSignIn() = RealmAuthentication.observeSignIn()
    fun signInOut() {
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