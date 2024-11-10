package navigation.hall.data

import navigation.hall.domain.model.AccountVerifyModel
import navigation.hall.domain.model.RegisterModel
import navigation.hall.domain.repository.RegisterRepository

class RegisterRepositoryImpl:RegisterRepository {
    override suspend fun register(model: RegisterModel): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyAccount(model: AccountVerifyModel): Result<String> {
        TODO("Not yet implemented")
    }
}