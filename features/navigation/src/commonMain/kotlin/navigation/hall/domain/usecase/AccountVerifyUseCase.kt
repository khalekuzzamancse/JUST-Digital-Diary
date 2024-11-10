@file:Suppress("unused")
package navigation.hall.domain.usecase

import navigation.hall.domain.model.AccountVerifyModel
import navigation.hall.domain.repository.RegisterRepository

class AccountVerifyUseCase(
    private val repository: RegisterRepository
) {
    suspend fun execute(model: AccountVerifyModel): Result<String> {
        return repository.verifyAccount(model)
    }
}