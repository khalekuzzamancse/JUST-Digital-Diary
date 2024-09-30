package database.entity

data class DepartmentEntity(
    val id: Int,
    val facultyId: String,
    val deptId: String,
    val shortname: String,
    val name: String,
    val membersCount: Int
)
