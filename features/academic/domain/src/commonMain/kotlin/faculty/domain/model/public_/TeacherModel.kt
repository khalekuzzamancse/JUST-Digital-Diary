package faculty.domain.model.public_

/**
 * @param id for ordering or sorting
 */


data class TeacherModel(
    val uid: String,
    val name: String,
    val email: String,
    val role: String,
    val phone: String,
    val achievement: String,
    val profile: String? = null,
    val additionalEmail: String? = null,
    val type: Int,
    val departments: List<DepartmentSubModel>
)
 data class DepartmentSubModel(
    val name: String,
    val shortname: String,
    val designation: String,
    val roomNo: String,
    val present: Int
)

