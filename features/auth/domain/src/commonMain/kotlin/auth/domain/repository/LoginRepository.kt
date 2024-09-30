package auth.domain.repository

import auth.domain.model.LoginModel


interface LoginRepository {
    /**
     * @return token on successful login
     */
    suspend fun login(model: LoginModel): Result<String>
}

