@file:Suppress("unused")
package schedule.ui.ui.admin.add_exam_schedule

import kotlinx.coroutines.flow.StateFlow
import schedule.ui.model.ExamScheduleModel

/**
 * - Manages the state and handles events related to form inputs
 * - Keeps an abstract `validator`, forcing the implementer to provide a separate implementation
 *   for validation. This ensures single responsibility and separation of concerns, so if any
 *   validation logic needs to change or need separate implementation for validate, there is no need to modify the `Controller`
 * - By defining the `validator` here, it also controls which `fields` should be validated
 * @property onCourseAddRequest Returns success or failure, allowing the form to be closed
 *   or kept open based on the result
 */

interface ExamScheduleFormController {
    val state: StateFlow<ExamScheduleModel>
    val validator: Validator
    val year: StateFlow<String>
    val semester: StateFlow<String>
    val session: StateFlow<String>

    //for a single course
    val courseCode: StateFlow<String>
    val courseTitle: StateFlow<String>
    val time: StateFlow<String>
    val date: StateFlow<String>

    //
    fun onSessionChanged(value: String)
    fun onYearChanged(value: String)
    fun onSemesterChanged(value: String)
    fun onCourseCodeChanged(value: String)
    fun onCourseTitleChanged(value: String)
    fun onTimeChanged(value: String)
    fun onDateChanged(value: String)
    fun onCourseAddRequest(): Boolean
    interface Validator {
        val areAllFieldsFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(
            year: StateFlow<String>,
            semester: StateFlow<String>,
            session: StateFlow<String>,
            //related to course
            courseCode: StateFlow<String>,
            courseTitle: StateFlow<String>,
            time: StateFlow<String>,
            date: StateFlow<String>
        )

    }


}