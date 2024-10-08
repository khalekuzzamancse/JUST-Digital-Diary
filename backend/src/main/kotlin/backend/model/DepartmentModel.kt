package backend.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Converts directly to JSON in the format expected by the backend,If the structure doesn't match,
 * the response will fail.Do not edit.
 */
@Serializable
internal data class DepartmentModel(
    @SerialName("id") val id: Int,
    @SerialName("dept_id") val deptId: String,
    @SerialName("name") val name: String,
    @SerialName("shortname") val shortName: String,
    @SerialName("membersCount") val membersCount: Int
)

/**
 * Converts directly to JSON in the format expected by the backend,If the structure doesn't match,
 * the response will fail.Do not edit.
 */
@Serializable
internal data class DepartmentListModel(
    @SerialName("faculty_name") val facultyName: String,
    @SerialName("departments") val departments: List<DepartmentModel>
)