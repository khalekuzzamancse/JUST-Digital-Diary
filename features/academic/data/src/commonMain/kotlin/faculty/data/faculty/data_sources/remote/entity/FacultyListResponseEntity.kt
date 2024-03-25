package faculty.data.faculty.data_sources.remote.entity

import kotlinx.serialization.Serializable
/**
 * * This will direcly converted to JSON as the same format
 * that the backend receive,if this structure is not matched
 * with backed the then response will be failed
 * * Do not edit it
 */
@Serializable
internal class FacultyListResponseEntity(
    val faculty: List<FacultyInfoResponseEntity>
)
/**
 * * This will direcly converted to JSON as the same format
 * that the backend receive,if this structure is not matched
 * with backed the then response will be failed
 * * Do not edit it
 */
@Serializable
internal data class FacultyInfoResponseEntity(
    val id: Int,
    val faculty_id: String,
    val name: String,
    val department_count: Int
)