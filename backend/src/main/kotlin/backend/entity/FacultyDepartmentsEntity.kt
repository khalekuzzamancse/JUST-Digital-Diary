package backend.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FacultyDataEntity(
    val faculties: Map<String, FacultyDepartmentsEntity> // Map with faculty ID as the key
)

@Serializable
data class FacultyDepartmentsEntity(
    @SerialName("departments") val departments: List<DepartmentEntity>
)

@Serializable
data class DepartmentEntity(
    @SerialName("id") val id: Int,
    @SerialName("dept_id") val deptId: String,
    @SerialName("name") val name: String,
    @SerialName("shortname") val shortName: String
)
