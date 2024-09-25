@file:Suppress("unused")

package database.entity
data class TokenEntity(
    val id: Int,   // Fixed primary key, ensuring only one token
    val token: String?
)

data class FacultyEntity(
    val facultyId: String,
    val id: Int,
    val name: String,
    val departmentCount: Int
)

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

data class DepartmentEntity(
    val id: Int,
    val facultyId: String,
    val deptId: String,
    val shortname: String,
    val name: String,
    val membersCount: Int
)
