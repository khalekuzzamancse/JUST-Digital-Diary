package schedule.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DepartmentEntity(
    @SerialName("dept_id") val deptId: String,
    val priority:Int,
    val name: String,
    val shortname: String,
)