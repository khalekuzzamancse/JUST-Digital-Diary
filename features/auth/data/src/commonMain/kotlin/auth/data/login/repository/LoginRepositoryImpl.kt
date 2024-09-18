package auth.data.login.repository

import auth.data.PackageLevelAccess
import auth.data.login.data_sources.remote.RemoteDataSource
import auth.data.login.dto.LoginResponseDTO
import auth.domain.login.model.LoginRequestModel
import auth.domain.login.model.LoginResponseModel
import auth.domain.login.repoisitory.LoginRepository
@OptIn(PackageLevelAccess::class) //RemoteDataSource,LoginResponseDTO within this data layer package
class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(model: LoginRequestModel): Result<LoginResponseModel> {
        val responseDTO: Result<LoginResponseDTO> = RemoteDataSource()
            .login(username = model.username, password = model.password)
        return if (responseDTO.isSuccess) onSuccess(responseDTO.getOrNull())
        else onFailure(responseDTO.exceptionOrNull())
    }

    private fun onSuccess(response: LoginResponseDTO?): Result<LoginResponseModel> {
        return if (response == null)
            Result.failure(Throwable("Success but Token==NULL at LoginRepositoryImpl:login()"))
        else
            Result.success(LoginResponseModel(response.token))
    }

    private fun onFailure(exception: Throwable?): Result<LoginResponseModel> {
        return Result.failure(
            exception = exception ?: Throwable("No Execution found at LoginRepositoryImpl:login()")
        )
    }

}