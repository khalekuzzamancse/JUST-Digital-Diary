@file:Suppress("unused")
package auth.domain.usecase

import auth.domain.repository.LoginRepository

class SendPasswordResetUseCase(
    private val repository: LoginRepository
) {
    suspend fun execute(email: String)=
        repository.sendResetPasswordRequest(email)
}