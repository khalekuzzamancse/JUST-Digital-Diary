package core.roomdb.entity
data class FacultyMemberEntity(
    val uid: String,
    val deptId: String,
    val name: String,
    val email: String?,
    val role: String,
    val phone: String?,
    val achievement: String?,
    val profile: String?,
    val additionalEmail: String?,
    val type: Int,
    val departments: List<DepartmentSubEntity> // Exposed departments entity
)

data class DepartmentSubEntity(
    val name: String,
    val shortname: String,
    val designation: String,
    val roomNo: String?,
    val present: Int
)

