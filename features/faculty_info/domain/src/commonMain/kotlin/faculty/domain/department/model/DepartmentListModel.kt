package faculty.domain.department.model

data class DepartmentListModel(
    val id: String,
    val name: String,
    val shortName: String,
    val employeeCount: Int=0
)