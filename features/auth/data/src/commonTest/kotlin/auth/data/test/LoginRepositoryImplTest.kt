package auth.data.test

import auth.data.factory.DataModuleFactory
import auth.domain.exception.CustomException
import auth.domain.model.LoginModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail


class LoginRepositoryImplTest {
    @Test
    fun failureTest() = runBlocking {
        val response = DataModuleFactory.createLoginRepository().login(
            LoginModel(
                "username", password = "password",
            )
        )
        response.fold(
            onSuccess = {
                println("Login successful:$it")
            },
            onFailure = { exception ->
                when(exception) {
                    is CustomException ->{
                        println(exception.message)
                    }
                    else -> {
                        println("Something went wrong:$exception")
                    }

                }
            }
        )
        assertTrue(response.isFailure)


    }
    @Test
    fun successTest() = runBlocking {
        val response = DataModuleFactory.createLoginRepository().login(
            LoginModel(
                "190142.cse@student.just.edu.bd", password = "12345678",
            )
        )
        response.fold(
            onSuccess = {
                println("Login successful:$it")
            },
            onFailure = {
                println("Failed to login:$it")
            }
        )
        assertTrue(response.isSuccess)


    }

}
