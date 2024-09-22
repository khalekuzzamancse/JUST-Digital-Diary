package schedule.ui.admin

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
        // Regular expression to match valid time formats: "3", "03", "3:30", "03:30", ":30"
        val timeRegex = """^(\d{1,2}|)(:[0-5]\d)?$""".toRegex()

        // Check length constraints (not more than 5 characters)
        if (time.length > 5) return false

        // Validate the format using regex
        if (!time.matches(timeRegex)) return false

        // Specific validation: Ensure only one or no ":" is present
        if (time.count { it == ':' } > 1) return false

        return true
    }
}