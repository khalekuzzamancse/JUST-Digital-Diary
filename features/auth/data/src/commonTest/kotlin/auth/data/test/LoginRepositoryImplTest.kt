package auth.data.test

import auth.data.login.repository.LoginRepositoryImpl
import auth.domain.login.model.LoginRequestModel
import auth.domain.login.model.LoginResponseModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue


class LoginRepositoryImplTest {
    @Test
    fun `login test`() = runBlocking {
        val repository = LoginRepositoryImpl()
        val response: Result<LoginResponseModel> =
            repository.login(LoginRequestModel(username = "smsourav", password = "test@123"))
        assertTrue(response.isSuccess)
        val result = response.getOrNull()
        assertTrue(result != null)
//        println("Response:$response")

    }

}
