@file:Suppress("unused")
package navigation.hall.domain.usecase

import navigation.hall.domain.model.ResetPasswordCodeValidateModel
import navigation.hall.domain.repository.LoginRepository

class ResetPasswordCodeValidateUseCase(
    private val repository: LoginRepository
) {
    suspend fun execute(model: ResetPasswordCodeValidateModel)=
        repository.validateResetPasswordCode(model)
}