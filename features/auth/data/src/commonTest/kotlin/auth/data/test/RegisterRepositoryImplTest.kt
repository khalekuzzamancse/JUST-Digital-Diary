package auth.data.test

import auth.data.repository.RegisterRepositoryImpl
import auth.domain.model.RegisterModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue


class RegisterRepositoryImplTest {
    @Test
    fun `register test`() = runBlocking {
        val userInfo = RegisterModel(
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
