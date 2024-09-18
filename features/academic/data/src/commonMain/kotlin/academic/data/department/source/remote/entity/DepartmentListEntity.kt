package academic.data.department.source.remote.entity

import academic.data.PackageLevelAccess
import kotlinx.serialization.Serializable

//response JSON Format
/**
 * * This will direcly converted to JSON as the same format
 * that the backend receive,if this structure is not matched
 * with backed the then response will be failed
 * * Do not edit it
 */
@PackageLevelAccess//should not access from  other  package
@Serializable
internal data class DepartmentListEntity(
    val faculty_name:String,
    val departments: List<DepartmentInfoEntity>
)