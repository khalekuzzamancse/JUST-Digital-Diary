package faculty.domain.usecase.admin

import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.repository.AdminRepository

class UpdateFacultyUseCase(
    private val repository: AdminRepository
) {
    suspend fun execute(facultyId:String,model: FacultyEntryModel) = repository.updateFaculty(facultyId,model)
}