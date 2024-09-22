package schedule.domain.model

data class ExamDetailsModel(
    val courseCode: String,
    val courseTitle: String,
    val time: String,
    val date: String,
)

data class ExamScheduleModel(
    val dept: String,
    val session: String,
    val year:String,
    val semester:String,
    val exams: List<ExamDetailsModel>,
)
