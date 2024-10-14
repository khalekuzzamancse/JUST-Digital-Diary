package faculty.domain.usecase.admin

import faculty.domain.repository.AdminRepository

class ReadTeacherUseCase(
    private val repository:AdminRepository
) {
    suspend fun execute(id: String)=repository.readTeacher(id)
}