package schedule.presentationlogic.model

/**
 * @property deptName will be used by UI ..... dept=full name+short name
 */
data class ClassScheduleModel(
    val deptName: String,
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
        return deptName.isBlank() && session.isBlank() && year.isBlank() && semester.isBlank()
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


data class ClassDetailModel(
    val courseCode: String,
    val time: String,
    val teacherName: String,
    val durationInHours: Int // Duration of the class in hours
) {
    /**
     * A string can be empty or blank, especially during editing. Empty or blank strings can cause rendering issues
     * because the string is used to measure size before being laid out
     */
    fun allFieldNotBlank(): Boolean {
        return !(courseCode.isBlank() && time.isBlank() && teacherName.isBlank())
    }
}

