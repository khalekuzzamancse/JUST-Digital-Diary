package data.schema

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Converts directly to JSON in the format expected by the backend,If the structure doesn't match,
 * the response will fail.Do not edit.
 */
@kotlinx.serialization.Serializable
class FacultyListResponseEntity(
    val faculty: List<FacultyInfoResponseEntity>
)

/**
 * Converts directly to JSON in the format expected by the backend,If the structure doesn't match,
 * the response will fail.Do not edit.
 */
@Serializable
data class FacultyInfoResponseEntity(
    @SerialName("id") val id: Int,
    @SerialName("faculty_id") val facultyId: String,
    @SerialName("name") val name: String,
    @SerialName("department_count") val departmentCount: Int
)