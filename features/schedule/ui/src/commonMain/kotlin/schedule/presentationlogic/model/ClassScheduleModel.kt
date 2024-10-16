package schedule.presentationlogic.model

/**
 * @property deptName will be used by UI ..... dept=full name+short name
 */
data class ClassScheduleModel(
    val deptName: String,
    val session: String,
    val year: String,
    val semester: String,
    val routine: List<ClassModel>
) {
    /**
     * A string can be empty or blank, especially during editing. Empty or blank strings can cause rendering issues
     * because the string is used to measure size before being laid out
     */
    fun areFieldsEmptyOrBlank(): Boolean {
        return deptName.isBlank() && session.isBlank() && year.isBlank() && semester.isBlank()
    }
}