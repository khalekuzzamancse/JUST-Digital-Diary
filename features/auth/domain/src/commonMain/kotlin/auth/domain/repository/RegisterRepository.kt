package auth.domain.repository

import auth.domain.model.RegisterModel


interface RegisterRepository {
    /**
     * Return true if success ,otherwise return the Result.failure with causes
     */
    suspend fun register(model: RegisterModel): Result<String>
}

