package schedule.ui.model

data class ExamDetailsModel(
    val courseCode: String,
    val courseTitle: String,
    val time: String,
    val date: String,
){
    // Method to check if any field is blank
    fun allFieldAvailable(): Boolean {
        return courseCode.isNotBlank() && courseTitle.isNotBlank() && time.isNotBlank() && date.isNotBlank()
    }

}

data class ExamScheduleModel(
    val dept: String,
    val session: String,
    val year:String,
    val semester:String,
    val exams: List<ExamDetailsModel>,
)
