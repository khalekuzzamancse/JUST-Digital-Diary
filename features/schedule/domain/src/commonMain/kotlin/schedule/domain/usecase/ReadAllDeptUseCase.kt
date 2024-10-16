package schedule.domain.usecase

import schedule.domain.repository.Repository

class ReadAllDeptUseCase(
    private val repository:Repository
) {
    suspend fun execute() =repository.readAllDept()
}