package schedule.presentationlogic.model





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

