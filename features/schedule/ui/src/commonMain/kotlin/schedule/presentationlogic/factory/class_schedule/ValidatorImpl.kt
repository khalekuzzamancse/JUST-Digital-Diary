package schedule.presentationlogic.factory.class_schedule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import schedule.presentationlogic.controller.ClassScheduleController

class ValidatorImpl : ClassScheduleController.Validator {

    private val _areAllFieldsFilled = MutableStateFlow(false)
    private val _errors = MutableStateFlow<List<String>>(emptyList())


    override val areAllFieldsFilled: StateFlow<Boolean> = _areAllFieldsFilled.asStateFlow()

    override val errors: StateFlow<List<String>> = _errors.asStateFlow()

    override fun observeFieldChanges(
        courseCodeFlow: StateFlow<String>,
        teacherNameFlow: StateFlow<String>,
        startTimeFlow: StateFlow<String>,
        endTimeFlow: StateFlow<String>
    ) {
        combine(
            courseCodeFlow,
            teacherNameFlow,
            startTimeFlow,
            endTimeFlow
        ) { fields: Array<String> ->
            fields
        }.onEach { fields ->
            val allFieldsFilled = fields.all { it.isNotBlank() }
            _areAllFieldsFilled.value = allFieldsFilled
            val errors = mutableListOf<String>()
            errors.addAll(
                validateFields(
                    startTime = fields[2],
                    endTime = fields[3]
                ))
            _errors.value = errors
        }.launchIn(CoroutineScope(Dispatchers.Default)) // Launch in the background scope
    }

    private fun validateFields(
       startTime: String, endTime: String
    ): List<String> {
        val errorMessages = mutableListOf<String?>()
        errorMessages.add(validateTimeOrError(startTime))
        errorMessages.add(validateTimeOrError(endTime))
        if (startTime.isNotBlank() && startTime == endTime)
            errorMessages.add("Start and end time must be different")
        return errorMessages.filterNotNull()//error message
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
