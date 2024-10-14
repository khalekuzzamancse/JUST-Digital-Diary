package faculty.domain.usecase.admin

import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.repository.AdminRepository

class InsertTeacherUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(model: TeacherEntryModel) = repository.addTeacher(model)
}
