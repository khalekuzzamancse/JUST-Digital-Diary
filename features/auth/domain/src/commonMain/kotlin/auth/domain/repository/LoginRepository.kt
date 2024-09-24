package auth.domain.repository

import auth.domain.model.LoginModel


interface LoginRepository {
    suspend fun login(model: LoginModel): Result<String>
}

