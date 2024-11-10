@file:Suppress("unused")
package navigation.hall.domain.usecase

import navigation.hall.domain.repository.LoginRepository

class SendPasswordResetUseCase(
    private val repository: LoginRepository
) {
    suspend fun execute(email: String)=
        repository.sendResetPasswordRequest(email)
}