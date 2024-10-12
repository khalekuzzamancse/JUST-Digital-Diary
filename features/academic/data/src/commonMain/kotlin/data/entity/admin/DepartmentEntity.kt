package data.entity.admin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * - It maintain the  he format of admin api
 */
@Serializable
data class DepartmentEntity(
    @SerialName("priority") val priority: Int,
    @SerialName("dept_id") val deptId: String,
    @SerialName("name") val name: String,
    @SerialName("shortname") val shortname: String
)
