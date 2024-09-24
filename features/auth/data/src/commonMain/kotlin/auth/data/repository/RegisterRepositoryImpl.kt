package auth.data.repository

import auth.domain.model.RegisterModel
import auth.domain.repository.RegisterRepository

class RegisterRepositoryImpl : RegisterRepository {
    override suspend fun register(model: RegisterModel): Result<String> {
        TODO()
    }

}