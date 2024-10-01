package domain.usecase

import domain.repository.Repository

class GetProfileUseCase(
    private val repository: Repository
) {
    suspend fun execute() = repository.retrieveProfile()
}