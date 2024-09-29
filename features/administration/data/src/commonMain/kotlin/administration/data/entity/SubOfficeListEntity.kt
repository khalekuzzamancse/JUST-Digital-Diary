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
data class SubOfficeListEntity(
    val sub_offices: List<SubOfficeEntity>
)

/**
 * This class will be directly converted to a JSON format that matches the backend's expectations.
 * If the structure does not align with the backend's requirements, the response will fail.
 * Do not modify this class unless there is a change in the backend's JSON format.
 */
@PackageLevelAccess // Restricts access outside this package
@Serializable
data class SubOfficeEntity(
    val id: Int,
    val sub_office_id: String,
    val name: String,
    val office_members_count: Int
)