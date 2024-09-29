package administration.data.entity

import administration.data.PackageLevelAccess
import kotlinx.serialization.Serializable
/**
 * This class will be directly converted to a JSON format that matches the backend's expectations.
 * If the structure does not align with the backend's requirements, the response will fail.
 * Do not modify this class unless there is a change in the backend's JSON format.
 */
@PackageLevelAccess // Restricts access outside this package
@Serializable
internal data class AdminOfficerListEntity(
    val officeMembers: List<AdminOfficerEntity>
)
/**
 * This class will be directly converted to a JSON format that matches the backend's expectations.
 * If the structure does not align with the backend's requirements, the response will fail.
 * Do not modify this class unless there is a change in the backend's JSON format.
 */
@PackageLevelAccess // Restricts access outside this package
@Serializable
internal data class AdminOfficerEntity (
    val uid: String,
    val name: String,
    val email: String,
    val additional_email: String?,
    val profile_image: String?,
    val achievement: String,
    val phone: String?,
    val designations: String,
    val room_no: String
)