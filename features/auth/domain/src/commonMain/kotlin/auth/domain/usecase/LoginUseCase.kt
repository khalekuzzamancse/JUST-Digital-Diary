package auth.domain.usecase

import auth.domain.model.LoginModel
import auth.domain.repository.LoginRepository

class LoginUseCase(
    private val repository: LoginRepository
) {
    /**
     * @return  token on success
     */
    suspend fun execute(model: LoginModel): Result<String>{
        return repository.login(model)
    }
}