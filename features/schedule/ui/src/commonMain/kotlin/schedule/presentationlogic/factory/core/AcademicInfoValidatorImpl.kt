@file:Suppress("unused")
package schedule.presentationlogic.factory.core
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import schedule.presentationlogic.controller.core.AcademicInfoController

class AcademicInfoValidatorImpl : AcademicInfoController.Validator {

    private val _areAllFieldsFilled = MutableStateFlow(false)
    private val _errors = MutableStateFlow<List<String>>(emptyList())

    override val areAllFieldsFilled: StateFlow<Boolean> = _areAllFieldsFilled.asStateFlow()

    override val errors: StateFlow<List<String>> = _errors.asStateFlow()

    override fun observeFieldChanges(
        year: StateFlow<String>,
        semester: StateFlow<String>,
        session: StateFlow<String>,
        selectedDeptIndex: StateFlow<Int?>
    ) {
        combine(
            year,
            semester,
            session,
            selectedDeptIndex.map { it?.toString()?:"" }
        ) { fields: Array<String> ->
            fields
        }.onEach { fields:Array<String> ->
            //TODO:Check the selected dept index too
            val allFieldsFilled = fields.all { it.isNotBlank() }
            _areAllFieldsFilled.value = allFieldsFilled
            val errors = mutableListOf<String>()
            errors.addAll(
                validateFields(
                    year = fields[0], semester = fields[1], session = fields[2]
                )
            )
            _errors.value = errors
        }.launchIn(CoroutineScope(Dispatchers.Default)) // Launch in the background scope
    }

    private fun validateFields(
        year: String, semester: String, session: String
    ): List<String> {
        val errorMessages = mutableListOf<String?>()
        errorMessages.add(validYearOrError(year))
        errorMessages.add(validateSemesterOrError(semester))
        errorMessages.add(validateSessionOrError(session))
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

    /**Returns if any error as message  or null if no error*/
    private fun validateSessionOrError(session: String): String? {
        return if (session.isBlank())
            return null//no error on blank
        else null //TODO:Check is valid session or not such as all digit and separate by -
    }
}
