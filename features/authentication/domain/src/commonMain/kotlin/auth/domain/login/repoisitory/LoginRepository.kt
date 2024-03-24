package auth.domain.login.repoisitory

import auth.domain.login.model.LoginRequestModel
import auth.domain.login.model.LoginResponseModel


interface LoginRepository {
    suspend fun login(model: LoginRequestModel): Result<LoginResponseModel>
}

