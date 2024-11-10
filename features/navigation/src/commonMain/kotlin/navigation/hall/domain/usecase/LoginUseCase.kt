package navigation.hall.domain.usecase

import navigation.hall.domain.model.LoginModel
import navigation.hall.domain.repository.LoginRepository

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