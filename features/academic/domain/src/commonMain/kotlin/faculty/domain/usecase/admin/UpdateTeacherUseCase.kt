package faculty.domain.usecase.admin

import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.repository.AdminRepository

class UpdateTeacherUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(teacherId:String,model: TeacherEntryModel) = repository.updateTeacher(teacherId,model)
}
