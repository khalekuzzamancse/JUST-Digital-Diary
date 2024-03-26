package common.data.token;

import core.network.post.post
import kotlinx.serialization.Serializable

/**
 * This structure should not be changed,this JSON structure of backend
 * * here is the back-end response
 * }
 */


@Serializable
private data class LoginResponseEntity(
    val jwt: String,
    val role_id: String,
    val  role:String
)
/**
 * this body will convert  to json as
 * Make sure that it structure is same as the back-end JSON

 * }
 */
@Serializable
private class LoginRequestEntity(
    val identifier: String,
    val password: String
)

/**
 * Using separate class or function to avoid coupling with external module and managing the central
 * place of token getter
 */
class TokenRetriever {
    private val loginUrl = "https://diary.rnzgoldenventure.com/api/users/login"
    suspend fun tokenRequest(username: String, password: String): Result<String> {

        val response = post<LoginResponseEntity>(
            url = loginUrl,
            body = LoginRequestEntity(identifier = username, password = password)
        )
        //on Success,
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else
            onFailure(response.exceptionOrNull())

    }
    private fun onSuccess(response: LoginResponseEntity?): Result<String> {
        return if (response == null)
            Result.failure(Throwable("Success but LoginResponseEntity==NULL at RemoteDataSource:login()"))
        else
            Result.success(response.jwt)
    }


    private fun onFailure(exception: Throwable?): Result<String> {
        return Result.failure(
            exception = exception ?: Throwable("Failure but No Execution found at RemoteDataSource:login()")
        )
    }
}