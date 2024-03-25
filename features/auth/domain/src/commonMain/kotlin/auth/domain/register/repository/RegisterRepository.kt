package auth.domain.register.repository

import auth.domain.register.model.RegisterRequestModel


interface RegisterRepository {
    /**
     * Return true if success ,otherwise return the Result.failure with causes
     */
    suspend fun register(model: RegisterRequestModel): Result<String>
}

