package faculty.domain.model.public_

/**
 * @param priority for sorting or ordering
 */
data class DepartmentModel(
    val priority:Int=0,
    val deptId: String,
    val name: String,
    val shortName: String,
    val employeeCount: Int
)