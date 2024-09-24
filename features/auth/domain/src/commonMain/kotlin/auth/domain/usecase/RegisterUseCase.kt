package auth.domain.usecase

import auth.domain.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
}