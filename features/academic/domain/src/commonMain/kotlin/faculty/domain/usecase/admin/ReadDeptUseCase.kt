package faculty.domain.usecase.admin

import faculty.domain.repository.AdminRepository

class ReadDeptUseCase(
    private val repository:AdminRepository
) {
    suspend fun execute(id: String)=repository.readDept(id)
}