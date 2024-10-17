package faculty.domain.usecase.admin

import faculty.domain.model.FacultyWriteModel
import faculty.domain.repository.AdminRepository

class InsertFacultyUseCase(
    private val repository: AdminRepository
){
    suspend fun execute(model: FacultyWriteModel)=repository.insertFaculty(model)
}