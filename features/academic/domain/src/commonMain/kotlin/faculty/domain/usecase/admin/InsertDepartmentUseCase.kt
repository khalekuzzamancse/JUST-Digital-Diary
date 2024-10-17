package faculty.domain.usecase.admin

import faculty.domain.model.DepartmentWriteModel
import faculty.domain.repository.AdminRepository

class InsertDepartmentUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(model: DepartmentWriteModel) = repository.insertDept(model)
}
