package faculty.domain.usecase.admin

import faculty.domain.model.TeacherWriteModel
import faculty.domain.repository.AdminRepository

class UpdateTeacherUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(teacherId:String,model: TeacherWriteModel) = repository.updateTeacher(teacherId,model)
}
