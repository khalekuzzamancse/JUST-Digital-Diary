package schedule.ui.ui.admin.add_class_schedule

import kotlinx.coroutines.flow.StateFlow
import schedule.ui.model.ClassScheduleModel

/**
 * - Manages the state and handles events related to form inputs
 * - Keeps an abstract `validator`, forcing the implementer to provide a separate implementation
 *   for validation. This ensures single responsibility and separation of concerns, so if any
 *   validation logic needs to change or need separate implementation for validate, there is no need to modify the `Controller`
 * - By defining the `validator` here, it also controls which `fields` should be validated
 * @property onCourseAddRequest Returns success or failure, allowing the form to be closed
 *   or kept open based on the result
 */

interface ClassScheduleFormController {
    val days: List<String>
        get() = listOf("Sat", "Sun", "Mon", "Tue", "Wed")
    val state: StateFlow<ClassScheduleModel>
    val validator: Validator
    val courseCode: StateFlow<String>
    val selectedDayIndex: StateFlow<Int>
    val teacherName: StateFlow<String>
    val startTime: StateFlow<String>
    val endTime: StateFlow<String>
    val year: StateFlow<String>
    val semester: StateFlow<String>
    val session: StateFlow<String>
    fun onSessionChanged(value: String)
    fun onYearChanged(value: String)
    fun onSemesterChanged(value: String)
    fun onCourseAddRequest(): Boolean
    fun onCourseCodeChanged(value: String)
    fun onSelectedDayIndexChanged(value: Int)
    fun onTeacherNameChanged(value: String)
    fun onStartTimeChanged(value: String)
    fun onEndTimeChanged(value: String)

    interface Validator {
        val areAllFieldsFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(
            courseCodeFlow: StateFlow<String>,
            yearFlow: StateFlow<String>,
            semesterFlow: StateFlow<String>,
            sessionFlow: StateFlow<String>,
            teacherNameFlow: StateFlow<String>,
            startTimeFlow: StateFlow<String>,
            endTimeFlow: StateFlow<String>
        )

    }


}
