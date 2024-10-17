package schedule.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class ClassScheduleWriteEntity(
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<ClassEntity>
)

