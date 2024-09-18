package faculty.domain.faculties.model

data class FacultyInfoModel(
    val id: Int,
    val facultyId: String,
    val name: String,
    val departmentsCount: Int
)