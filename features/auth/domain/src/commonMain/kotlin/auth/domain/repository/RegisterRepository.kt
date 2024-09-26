package auth.domain.repository

import auth.domain.model.RegisterModel


interface RegisterRepository {
    /**
     * @return message from server or CustomException
     */
    suspend fun register(model: RegisterModel): Result<String>
}

