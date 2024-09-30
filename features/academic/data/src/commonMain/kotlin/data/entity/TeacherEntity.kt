package data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class DepartmentSubEntity(
    val name: String,
    val shortname: String,
    val designation: String,
    val room_no: String,
    val present: Int
)

@Serializable
internal data class TeacherEntity(
    val uid: String,
    val name: String,
    val email: String,
    val role: String,
    val phone: String,
    val achievement: String,
    val profile: String? = null,
    val additional_email: String? = null,
    val type: Int,
    val departments: List<DepartmentSubEntity>
)

@Serializable
internal data class TeacherListEntity(
    val facultyMembers: List<TeacherEntity>
)
