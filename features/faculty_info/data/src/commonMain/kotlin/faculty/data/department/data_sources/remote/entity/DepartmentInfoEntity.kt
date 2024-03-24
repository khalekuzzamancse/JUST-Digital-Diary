package faculty.data.department.data_sources.remote.entity

import faculty.data.PackageLevelAccess
import kotlinx.serialization.Serializable
/**
 * * This will direcly converted to JSON as the same format
 * that the backend receive,if this structure is not matched
 * with backed the then response will be failed
 * * Do not edit it
 */
//response JSON Format
@PackageLevelAccess//do not access outside package
@Serializable
internal data class DepartmentInfoEntity(
    val id: Int,
    val dept_id: String,
    val name: String,
    val shortname: String
)