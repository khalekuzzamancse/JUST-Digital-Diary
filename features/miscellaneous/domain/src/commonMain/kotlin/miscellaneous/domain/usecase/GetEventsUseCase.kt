package miscellaneous.domain.usecase

import miscellaneous.domain.repoisitory.Repository

class GetEventsUseCase(
    private val repository:Repository
) {
    suspend fun execute()=repository.getEvents()
}