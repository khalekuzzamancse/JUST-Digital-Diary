package navigation.hall.domain.usecase

import navigation.hall.domain.model.RegisterModel
import navigation.hall.domain.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
   suspend fun execute(model: RegisterModel):Result<String>{
        return repository.register(model)
    }
}