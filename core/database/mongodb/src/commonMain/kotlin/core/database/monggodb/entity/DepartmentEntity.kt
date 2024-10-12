package core.database.monggodb.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DepartmentEntity(
    @SerialName("priority") val priority: Int,
    @SerialName("dept_id") val deptId: String,
    @SerialName("name") val name: String,
    @SerialName("shortname") val shortName: String
)
