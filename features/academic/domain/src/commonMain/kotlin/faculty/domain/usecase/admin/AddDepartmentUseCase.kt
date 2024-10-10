package faculty.domain.usecase.admin

import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.repository.AdminRepository

class AddDepartmentUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(model: DepartmentEntryModel) = repository.addDepartment(model)
}
