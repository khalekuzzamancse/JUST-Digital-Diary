package schedule.domain.model
data class ClassScheduleModel(
    val dept: String,
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<DailyClassScheduleModel>

)
data class ClassDetailModel(
    val subject: String,
    val time: String,
    val room: String,
    val durationInHours: Int // Duration of the class in hours
)

data class DailyClassScheduleModel(
    val day: String,
    val items: List<ClassDetailModel>
)
