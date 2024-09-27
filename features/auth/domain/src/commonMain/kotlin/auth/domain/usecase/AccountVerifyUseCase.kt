@file:Suppress("unused")
package auth.domain.usecase

import auth.domain.model.AccountVerifyModel
import auth.domain.repository.RegisterRepository

class AccountVerifyUseCase(
    private val repository: RegisterRepository
) {
    suspend fun execute(model: AccountVerifyModel): Result<String> {
        return repository.verifyAccount(model)
    }
}