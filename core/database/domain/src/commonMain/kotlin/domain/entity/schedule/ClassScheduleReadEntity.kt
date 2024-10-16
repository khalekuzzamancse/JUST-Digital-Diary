package domain.entity.schedule

import kotlinx.serialization.Serializable

/**
 * @property id useful for admin to delete or update
 */
@Serializable
data class ClassScheduleReadEntity(
    val id: String,
    val deptName: String,
    val deptShortName: String,
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<Class>
)

