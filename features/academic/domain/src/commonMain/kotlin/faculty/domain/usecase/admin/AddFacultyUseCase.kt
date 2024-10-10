package faculty.domain.usecase.admin

import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.repository.AdminRepository

class AddFacultyUseCase(
    private val repository: AdminRepository
){
    suspend fun execute(model:FacultyEntryModel)=repository.addFaculty(model)
}