package faculty.domain.usecase.admin

import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.repository.AdminRepository

class AddTeacherUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(model: TeacherEntryModel) = repository.addTeacher(model)
}
