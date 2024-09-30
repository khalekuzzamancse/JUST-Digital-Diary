package schedule.ui.model
data class ClassScheduleModel(
    val dept: String,
    val session: String,
    val year: String,
    val semester: String,
    val routine: List<DailyClassScheduleModel>
) {
    /**
     * A string can be empty or blank, especially during editing. Empty or blank strings can cause rendering issues
     * because the string is used to measure size before being laid out
     */
    fun areFieldsEmptyOrBlank(): Boolean {
        return dept.isBlank() && session.isBlank() && year.isBlank() && semester.isBlank()
    }
}

data class ClassDetailModel(
    val courseCode: String,
    val time: String,
    val teacherShortName: String,
    val durationInHours: Int // Duration of the class in hours
) {
    /**
     * A string can be empty or blank, especially during editing. Empty or blank strings can cause rendering issues
     * because the string is used to measure size before being laid out
     */
    fun allFieldNotBlank(): Boolean {
        return !(courseCode.isBlank() && time.isBlank() && teacherShortName.isBlank())
    }
}

data class DailyClassScheduleModel(
    val day: String,
    val items: List<ClassDetailModel>
) {
    /**
     * A string can be empty or blank, especially during editing. Empty or blank strings can cause rendering issues
     * because the string is used to measure size before being laid out
     */
    fun areFieldsEmptyOrBlank(): Boolean {
        return day.isBlank() && items.all { it.allFieldNotBlank() }
    }
    /**
     * A string can be empty or blank, especially during editing. Empty or blank strings can cause rendering issues
     * because the string is used to measure size before being laid out
     */
    fun isDayIsBlank(): Boolean {
        return day.isBlank()
    }
    fun dayIsNotBlack(): Boolean {
        return day.isNotBlank()
    }
}
