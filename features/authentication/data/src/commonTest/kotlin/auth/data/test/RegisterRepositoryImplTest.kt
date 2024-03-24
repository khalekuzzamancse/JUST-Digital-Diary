package auth.data.test

import auth.data.login.repository.LoginRepositoryImpl
import auth.data.register.repoisitory.RegisterRepositoryImpl
import auth.domain.login.model.LoginRequestModel
import auth.domain.login.model.LoginResponseModel
import auth.domain.register.model.RegisterRequestModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue


class RegisterRepositoryImplTest {
    @Test
    fun `register test`() = runBlocking {
        val userInfo = RegisterRequestModel(
            name = "Md Khalekuzzaman",
            email = "190142.cse@student.just.edu.bd",
            username = "khalek190142",
            password = "test@123",
        )
        val repository = RegisterRepositoryImpl()
        val response= repository.register(userInfo)
        println("Response:$response")
        assertTrue(response.isSuccess)
        val result = response.getOrNull()
        assertTrue(result != null)


    }

}
