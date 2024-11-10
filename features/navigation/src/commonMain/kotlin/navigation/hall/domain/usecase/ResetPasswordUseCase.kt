@file:Suppress("unused")
package navigation.hall.domain.usecase

import navigation.hall.domain.model.ResetPasswordModel
import navigation.hall.domain.repository.LoginRepository

class ResetPasswordUseCase(
    private val repository: LoginRepository
) {
    suspend fun execute(model: ResetPasswordModel)=
        repository.resetPassword(model)
}