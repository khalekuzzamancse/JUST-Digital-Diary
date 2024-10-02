@file:Suppress("unused")
package auth.domain.usecase

import auth.domain.model.ResetPasswordModel
import auth.domain.repository.LoginRepository

class ResetPasswordUseCase(
    private val repository: LoginRepository
) {
    suspend fun execute(model: ResetPasswordModel)=
        repository.resetPassword(model)
}