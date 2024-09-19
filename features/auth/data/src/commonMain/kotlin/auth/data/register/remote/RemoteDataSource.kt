package auth.data.register.remote

import auth.data.PackageLevelAccess
import auth.data.register.remote.entity.RegisterRequestEntity
import auth.data.register.remote.entity.RegisterResponseEntity
import _old.network.post.post

@PackageLevelAccess
internal class RemoteDataSource {
    private val url = "https://diary.rnzgoldenventure.com/api/users/register"

    /**
     * return the success message or failure message as String
     */
    suspend fun register(body: RegisterRequestEntity): Result<String> {
        val response: Result<RegisterResponseEntity> = post<RegisterResponseEntity>(
            url = url,
            body = body
        )
        return if (response.isSuccess) onSuccess(response.getOrNull())
        else onFailure(response.exceptionOrNull())
    }

    private fun onSuccess(response: RegisterResponseEntity?): Result<String> {
        return if (response == null)
            Result.failure(Throwable("Success but LoginResponseEntity==NULL at RemoteDataSource:login()"))
        else
            Result.success(response.message)
    }

    private fun onFailure(exception: Throwable?): Result<String> {
        return Result.failure(
            exception = exception
                ?: Throwable("Failure but No Execution found at RemoteDataSource:login()")
        )
    }
}