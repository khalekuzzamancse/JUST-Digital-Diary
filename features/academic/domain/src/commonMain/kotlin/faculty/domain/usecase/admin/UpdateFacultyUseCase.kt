package faculty.domain.usecase.admin

import faculty.domain.model.FacultyWriteModel
import faculty.domain.repository.AdminRepository

class UpdateFacultyUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(facultyId:String,model: FacultyWriteModel) = repository.updateFaculty(facultyId,model)
}