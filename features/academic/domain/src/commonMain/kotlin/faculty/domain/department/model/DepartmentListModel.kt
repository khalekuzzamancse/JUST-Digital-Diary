package faculty.domain.department.model

/**
 * @param id for sorting or ordering
 */
data class DepartmentListModel(
    val id:Int=0,
    val deptId: String,
    val name: String,
    val shortName: String,
    val employeeCount: Int=0
)