package academic.data.department.source.remote.entity

import academic.data.PackageLevelAccess
import kotlinx.serialization.Serializable
/**
 * This class will be directly converted to a JSON format that matches the backend's expectations.
 * If the structure does not align with the backend's requirements, the response will fail.
 * Do not modify this class unless there is a change in the backend's JSON format.
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