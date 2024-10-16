package schedule.data.entity

import kotlinx.serialization.Serializable
@Serializable
data class ExamDetailsSchema(
    val courseCode: String,
    val courseTitle: String,
    val time: String,
    val date: String,
)
@Serializable
data class ExamScheduleSchema(
    val dept: String,
    val session: String,
    val year:String,
    val semester:String,
    val exams: List<ExamDetailsSchema>,
)
