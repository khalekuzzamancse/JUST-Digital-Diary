package faculty.domain.usecase.admin

import faculty.domain.model.TeacherWriteModel
import faculty.domain.repository.AdminRepository

class InsertTeacherUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(model: TeacherWriteModel) = repository.insertTeacher(model)
}
