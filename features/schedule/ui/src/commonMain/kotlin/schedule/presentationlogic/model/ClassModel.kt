package schedule.presentationlogic.model

data class ClassModel(
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