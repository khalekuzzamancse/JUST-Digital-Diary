package faculty.domain.usecase.admin

import faculty.domain.model.DepartmentWriteModel
import faculty.domain.repository.AdminRepository

class DeleteDepartmentUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(id:String) = repository.deleteDepartment(id)
}
