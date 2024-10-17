package faculty.domain.usecase.admin

import faculty.domain.model.DepartmentWriteModel
import faculty.domain.repository.AdminRepository

class UpdateDepartmentUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(deptId:String,model: DepartmentWriteModel) = repository.updateDepartment(deptId,model)
}