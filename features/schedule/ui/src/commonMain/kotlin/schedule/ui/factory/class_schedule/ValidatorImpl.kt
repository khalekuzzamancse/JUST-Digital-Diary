package schedule.ui.factory.class_schedule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import schedule.ui.ui.admin.add_class_schedule.ClassScheduleFormController

class ValidatorImpl : ClassScheduleFormController.Validator {

    private val _areAllFieldsFilled = MutableStateFlow(false)
    private val _errors = MutableStateFlow<List<String>>(emptyList())


    override val areAllFieldsFilled: StateFlow<Boolean> = _areAllFieldsFilled.asStateFlow()

    override val errors: StateFlow<List<String>> = _errors.asStateFlow()

    override fun observeFieldChanges(
        courseCodeFlow: StateFlow<String>,
        yearFlow: StateFlow<String>,
        semesterFlow: StateFlow<String>,
        sessionFlow: StateFlow<String>,
        teacherNameFlow: StateFlow<String>,
        startTimeFlow: StateFlow<String>,
        endTimeFlow: StateFlow<String>
    ) {
        combine(
            courseCodeFlow,
            yearFlow,
            semesterFlow,
            sessionFlow,
            teacherNameFlow,
            startTimeFlow,
            endTimeFlow
        ) { fields: Array<String> ->
            fields
        }.onEach { fields ->
            val allFieldsFilled = fields.all { it.isNotBlank() }
            _areAllFieldsFilled.value = allFieldsFilled
            val errors = mutableListOf<String>()
            errors.addAll(validateFields(fields[1], fields[2], fields[5], fields[6]))
            _errors.value = errors
        }.launchIn(CoroutineScope(Dispatchers.Default)) // Launch in the background scope
    }

    private fun validateFields(
        year: String, semester: String, startTime: String, endTime: String
    ): List<String> {
        val errorMessages = mutableListOf<String?>()
        errorMessages.add(validYearOrError(year))
        errorMessages.add(validateSemesterOrError(semester))
        errorMessages.add(validateTimeOrError(startTime))
        errorMessages.add(validateTimeOrError(endTime))
        if (startTime.isNotBlank() && startTime == endTime)
            errorMessages.add("Start and end time must be different")
        return errorMessages.filterNotNull()//error message
    }

    private fun validYearOrError(year: String): String? {
        if (year.isBlank())
            return null//no error on null
        return try {
            val yearInt = year.toInt()
            if (yearInt in 1..5)
                null//no error
            else
                "Valid year range=[1,5]"
        } catch (e: NumberFormatException) {
            "Year Should be number"
        }
    }

    private fun validateSemesterOrError(semester: String): String? {
        if (semester.isBlank())
            return null//no error on null
        return try {
            val int = semester.toInt()
            if (int in 1..2)
                null//no error
            else
                "Valid semester range=[1,2]"
        } catch (e: NumberFormatException) {
            "Semester Should be number"
        }
    }

    private fun validateTimeOrError(time: String): String? {
        if (time.isBlank())
            return null//no error on null
        val timePattern = """^\d{1,2}(:\d{1,2})?$""".toRegex()
        return if (!timePattern.matches(time))
            "Valid time format dd:dd"
        else null
    }
}
