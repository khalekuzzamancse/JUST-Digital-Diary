package academic.data.teacher.sources.remote.entity

import kotlinx.serialization.Serializable


@Serializable
data class TeacherEntity(
    val uid: String,
    val name: String,
    val username: String,
    val email: String,
    val achievement: String,
    val additional_email: String?,
    val phone: String,
    val profile_image: String,
    val room_no: Int,
    val type: Int,
    val designations: List<Designation>,
    val dept_id: String,
    val present: Int,
    val department_name: String,
    val shortname: String
)

@Serializable
data class Designation(
    val name: String
)

@Serializable
data class TeacherListEntity(
    val facultyMembers: List<TeacherEntity>
)

