@file:Suppress("UnUsed")
package schedule.presentationlogic.factory.exam_schedule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import schedule.presentationlogic.controller.ExamScheduleInsertController

class ValidatorImpl : ExamScheduleInsertController.Validator {
    private val _areAllFieldsFilled = MutableStateFlow(false)
    override val areAllFieldsFilled = _areAllFieldsFilled.asStateFlow()

    private val _errors = MutableStateFlow<List<String>>(emptyList())
    override val errors = _errors.asStateFlow()

    override fun observeFieldChanges(
        year: StateFlow<String>,
        semester: StateFlow<String>,
        session: StateFlow<String>,
        courseCode: StateFlow<String>,
        courseTitle: StateFlow<String>,
        time: StateFlow<String>,
        date: StateFlow<String>
    ) {
        combine(
            year,
            semester,
            session,
            courseCode,
            courseTitle,
            time,
            date
        ) { fields: Array<String> ->
            fields
        }.onEach { fields ->
            val allFieldsFilled = fields.all { it.isNotBlank() }
            _areAllFieldsFilled.value = allFieldsFilled

            val errors = mutableListOf<String>()
            errors.addAll(validateFields(fields[0], fields[1], fields[5]))
            _errors.value = errors

        }.launchIn(CoroutineScope(Dispatchers.Default)) // Launch in the background scope
    }
    private fun validateFields(
        year: String, semester: String, time: String,
    ): List<String> {
        val errorMessages = mutableListOf<String?>()
        errorMessages.add(validYearOrError(year))
        errorMessages.add(validateSemesterOrError(semester))
        errorMessages.add(validateTimeOrError(time))
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