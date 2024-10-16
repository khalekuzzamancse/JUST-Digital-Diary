package schedule.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ClassScheduleWriteEntity(
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<ClassEntity>

)
@Serializable
data class ClassEntity(
    val day: String,
    val items: List<ClassDetailEntity>
)

@Serializable
data class ClassDetailEntity(
    val subject: String,
    val time: String,
    val room: String,
    val durationInHours: Int // Duration of the class in hours
)
