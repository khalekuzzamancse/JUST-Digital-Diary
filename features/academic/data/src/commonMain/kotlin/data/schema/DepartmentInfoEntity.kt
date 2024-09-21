package data.schema

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Converts directly to JSON in the format expected by the backend,If the structure doesn't match,
 * the response will fail.Do not edit.
 */
@Serializable
internal data class DepartmentInfoEntity(
    @SerialName("id") val id: Int,
    @SerialName("dept_id") val deptId: String,
    @SerialName("name") val name: String,
    @SerialName("shortname") val shortName: String
)

/**
 * Converts directly to JSON in the format expected by the backend,If the structure doesn't match,
 * the response will fail.Do not edit.
 */
@Serializable
internal data class DepartmentListEntity(
    @SerialName("faculty_name") val facultyName: String,
    @SerialName("departments") val departments: List<DepartmentInfoEntity>
)