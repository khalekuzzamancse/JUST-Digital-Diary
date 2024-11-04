package core.data.entity.schedule

import kotlinx.serialization.Serializable

@Serializable
data class ClassScheduleWriteEntity(
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<Class>
)

