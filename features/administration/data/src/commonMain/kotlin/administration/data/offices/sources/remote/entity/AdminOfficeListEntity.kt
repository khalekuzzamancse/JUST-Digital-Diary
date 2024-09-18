package administration.data.offices.sources.remote.entity

import administration.data.PackageLevelAccess
import kotlinx.serialization.Serializable
/**
 * This class will be directly converted to a JSON format that matches the backend's expectations.
 * If the structure does not align with the backend's requirements, the response will fail.
 * Do not modify this class unless there is a change in the backend's JSON format.
 */
@PackageLevelAccess // Restricts access outside this package
@Serializable
class AdminOfficeListEntity(
    val offices: List<AdminOfficeInfoEntity>
)

/**
 * This class will be directly converted to a JSON format that matches the backend's expectations.
 * If the structure does not align with the backend's requirements, the response will fail.
 * Do not modify this class unless there is a change in the backend's JSON format.
 */
@PackageLevelAccess // Restricts access outside this package
@Serializable
data class AdminOfficeInfoEntity(
    val id: Int,
    val office_id: String,
    val name: String,
    val sub_offices_count: Int
)

