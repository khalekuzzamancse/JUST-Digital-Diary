package schedule.data.schema

import kotlinx.serialization.Serializable

@Serializable
data class ClassScheduleSchema(
    val dept: String,
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<DailyClassScheduleSchema>

)
@Serializable
data class ClassDetailSchema(
    val subject: String,
    val time: String,
    val room: String,
    val durationInHours: Int // Duration of the class in hours
)
@Serializable
data class DailyClassScheduleSchema(
    val day: String,
    val items: List<ClassDetailSchema>
)
