package navigation.hall.domain.repository

import navigation.hall.domain.model.LoginModel
import navigation.hall.domain.model.ResetPasswordCodeValidateModel
import navigation.hall.domain.model.ResetPasswordModel



interface LoginRepository {
    /**
     * @return token on successful login
     */
    suspend fun login(model: LoginModel): Result<String>
    suspend fun sendResetPasswordRequest(email:String):Result<Unit>
    suspend fun resetPassword(model: ResetPasswordModel):Result<Unit>
    suspend fun validateResetPasswordCode(model: ResetPasswordCodeValidateModel): Result<Unit>
}
