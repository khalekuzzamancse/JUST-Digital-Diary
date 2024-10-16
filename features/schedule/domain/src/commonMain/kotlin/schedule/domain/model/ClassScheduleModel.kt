package schedule.domain.model
data class ClassScheduleModel(
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<ClassModel>

)
data class ClassModel(
    val day: String,
    val classes: List<ClassDetailModel>
)

data class ClassDetailModel(
    val subject: String,
    val time: String,
    val teacherName: String,
    val durationInHours: Int // Duration of the class in hours
)

