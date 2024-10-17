@file:Suppress("unused")
package faculty.domain.model

/**
 * @property priority need by admin while editing so that can edit the priority
 */
data class DepartmentReadModel(
    val name: String,
    val shortname: String,
    val numberOfEmployee: Int,
    val deptId: String,
    val facultyId: String,
    val priority:Int,
)