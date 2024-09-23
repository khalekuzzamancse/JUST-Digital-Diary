package schedule.ui.factory

class InputValidator {

    // Method to validate the year (1 to 5 inclusive) as a string
    fun validateYear(year: String): Boolean {
        return try {
            val yearInt = year.toInt()
            yearInt in 1..5
        } catch (e: NumberFormatException) {
            false
        }
    }

    // Method to validate the semester (1 to 10 inclusive) as a string
    fun validateSemester(semester: String): Boolean {
        return try {
            val semesterInt = semester.toInt()
            semesterInt in 1..10
        } catch (e: NumberFormatException) {
            false
        }
    }

    // Method to validate time as a string, examples: "03:30", "3:30", "3", "03", ":30"
    fun validateTime(time: String): Boolean {
        // Regular expression to match valid time formats: d:d, dd:dd, d, dd
        val timePattern = """^\d{1,2}(:\d{1,2})?$""".toRegex()

        // Check if the input matches the pattern
        return timePattern.matches(time)

        return true
    }
}