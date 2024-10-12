package faculty.domain.usecase.admin

import faculty.domain.repository.AdminRepository

class GetAllDepartmentUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute() = repository.getAllDept()
}
