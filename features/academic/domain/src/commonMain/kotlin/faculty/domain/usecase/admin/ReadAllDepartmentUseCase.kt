package faculty.domain.usecase.admin

import faculty.domain.repository.AdminRepository

class ReadAllDepartmentUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute() = repository.getAllDept()
}
