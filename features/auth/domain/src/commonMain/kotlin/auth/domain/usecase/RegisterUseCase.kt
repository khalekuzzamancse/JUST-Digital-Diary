package auth.domain.usecase

import auth.domain.model.RegisterModel
import auth.domain.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
   suspend fun execute(model: RegisterModel):Result<String>{
        return repository.register(model)
    }
}