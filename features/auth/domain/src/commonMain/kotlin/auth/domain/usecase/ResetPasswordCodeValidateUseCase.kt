@file:Suppress("unused")
package auth.domain.usecase

import auth.domain.model.ResetPasswordCodeValidateModel
import auth.domain.repository.LoginRepository

class ResetPasswordCodeValidateUseCase(
    private val repository: LoginRepository
) {
    suspend fun execute(model: ResetPasswordCodeValidateModel)=
        repository.validateResetPasswordCode(model)
}