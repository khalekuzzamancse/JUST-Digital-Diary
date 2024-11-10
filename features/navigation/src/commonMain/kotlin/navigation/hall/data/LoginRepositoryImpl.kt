package navigation.hall.data

import navigation.hall.domain.model.LoginModel
import navigation.hall.domain.model.ResetPasswordCodeValidateModel
import navigation.hall.domain.model.ResetPasswordModel
import navigation.hall.domain.repository.LoginRepository

class LoginRepositoryImpl:LoginRepository {
    override suspend fun login(model: LoginModel): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun sendResetPasswordRequest(email: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(model: ResetPasswordModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun validateResetPasswordCode(model: ResetPasswordCodeValidateModel): Result<Unit> {
        TODO("Not yet implemented")
    }
}