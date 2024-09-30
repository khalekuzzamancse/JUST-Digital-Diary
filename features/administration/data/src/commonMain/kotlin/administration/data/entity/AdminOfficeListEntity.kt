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
internal class AdminOfficeListEntity(
    val offices: List<OfficeEntity>
)

/**
 * This class will be directly converted to a JSON format that matches the backend's expectations.
 * If the structure does not align with the backend's requirements, the response will fail.
 * Do not modify this class unless there is a change in the backend's JSON format.
 */
@PackageLevelAccess // Restricts access outside this package
@Serializable
internal data class OfficeEntity(
    val id: Int,
    val office_id: String,
    val name: String,
    val sub_offices_count: Int
)
