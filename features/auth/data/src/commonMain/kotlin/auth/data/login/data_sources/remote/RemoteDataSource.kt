package auth.data.login.data_sources.remote

import auth.data.PackageLevelAccess
import auth.data.login.data_sources.remote.entity.LoginRequestEntity
import auth.data.login.data_sources.remote.entity.LoginResponseEntity
import auth.data.login.dto.LoginResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.post2
@OptIn(PackageLevelAccess::class)
 class RemoteDataSource {
    private val loginUrl = "https://diary.rnzgoldenventure.com/api/users/login"
    suspend fun login(username: String, password: String): Result<LoginResponseDTO> {
        val response = post2<LoginResponseEntity>(
            url = loginUrl,
            body = LoginRequestEntity(identifier = username, password = password)
        )
        //on Success,
        return if (response.isSuccess)
             onSuccess(response.getOrNull())
        else
            onFailure(response.exceptionOrNull())

    }

    private fun onSuccess(response: LoginResponseEntity?): Result<LoginResponseDTO> {
        return if (response == null)
            Result.failure(Throwable("Success but LoginResponseEntity==NULL at RemoteDataSource:login()"))
        else
            Result.success(LoginResponseDTO(response.jwt))
    }


    private fun onFailure(exception: Throwable?): Result<LoginResponseDTO> {
        return Result.failure(
            exception = exception
                ?: Throwable("Failure but No Execution found at RemoteDataSource:login()")
        )
    }
    suspend fun requestToken(username: String, password: String): String? {
        val response =login(username = username, password = password)
        val result = response.getOrNull()
        return result?.token
    }
}