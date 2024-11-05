package auth.domain.repository

import auth.domain.model.LoginModel
import auth.domain.model.ResetPasswordCodeValidateModel
import auth.domain.model.ResetPasswordModel



interface LoginRepository {
    /**
     * @return token on successful login
     */
    suspend fun login(model: LoginModel): Result<String>
    suspend fun sendResetPasswordRequest(email:String):Result<Unit>
    suspend fun resetPassword(model: ResetPasswordModel):Result<Unit>
    suspend fun validateResetPasswordCode(model: ResetPasswordCodeValidateModel): Result<Unit>
}
