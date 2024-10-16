package faculty.domain.usecase.admin

import faculty.domain.repository.AdminRepository

class ReadFacultyUseCase(
    private val repository:AdminRepository
) {
    suspend fun execute(id: String)=repository.readFaculty(id)
}