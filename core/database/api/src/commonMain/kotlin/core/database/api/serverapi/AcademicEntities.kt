package core.database.api.serverapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import common.docs.EntityDocs

//TODO:Related to faculties--------------------------------------------------------
/** Refer: [EntityDocs] */
@Serializable
internal data class FacultyReadEntity(
    @SerialName("id") val id: Int,
    @SerialName("faculty_id") val facultyId: String,
    @SerialName("name") val name: String,
    @SerialName("department_count") val departmentCount: Int
)


//TODO:Related to Department--------------------------------------------------------

/** Refer: [EntityDocs] */
@Serializable
internal data class DepartmentEntity(
    @SerialName("id") val id: Int,
    @SerialName("dept_id") val deptId: String,
    @SerialName("name") val name: String,
    @SerialName("shortname") val shortName: String,
    @SerialName("membersCount") val membersCount: Int
)

/** Refer: [EntityDocs] */
@Serializable
internal data class DepartmentListEntity(
    @SerialName("faculty_name") val facultyName: String,
    @SerialName("departments") val departments: List<DepartmentEntity>
)
/** Refer: [EntityDocs] */
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
/**More on [EntityDocs]*/
@Serializable
internal data class DepartmentSubEntity(
    val name: String,
    val shortname: String,
    val designation: String,
    val room_no: String,
    val present: Int
)
/**More on [EntityDocs]*/
@Serializable
internal data class TeacherListEntity(
    val facultyMembers: List<TeacherEntity>
)
