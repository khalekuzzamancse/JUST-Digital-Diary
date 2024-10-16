@file:Suppress("unused")

package schedule.presentationlogic.controller

import kotlinx.coroutines.flow.StateFlow
import schedule.presentationlogic.controller.core.AcademicInfoController
import schedule.presentationlogic.controller.core.CoreController
import schedule.presentationlogic.model.ClassScheduleModel

/**
 * - Manages the state and handles events related to form inputs
 * - Keeps an abstract `validator`, forcing the implementer to provide a separate implementation
 *   for validation. This ensures single responsibility and separation of concerns, so if any
 *   validation logic needs to change or need separate implementation for validate, there is no need to modify the `Controller`
 * - By defining the `validator` here, it also controls which `fields` should be validated
 * @property addToSchedule Returns success or failure, allowing the form to be closed
 *   or kept open based on the result
 */

interface ClassScheduleController:CoreController {
    val days: List<String>
        get() = listOf("Sat", "Sun", "Mon", "Tue", "Wed")

    /**Used to fill the academics info form*/
    val academicController: AcademicInfoController

    /**Used to to decide show the academic info form or schedule the add*/
    val isAcademicDetailsFilled: StateFlow<Boolean>
    val schedule: StateFlow<ClassScheduleModel>
    val validator: Validator
    val courseCode: StateFlow<String>
    val selectedDayIndex: StateFlow<Int>
    val teacherName: StateFlow<String>
    val startTime: StateFlow<String>
    val endTime: StateFlow<String>


    fun addToSchedule(): Boolean
    fun onCourseCodeChanged(value: String)
    fun onSelectedDayIndexChanged(value: Int)
    fun onTeacherNameChanged(value: String)
    fun onStartTimeChanged(value: String)
    fun onEndTimeChanged(value: String)

    /**Insert to database or server*/
    suspend fun insert()

    /**
     * Implementation Guild:
     * - Update the dept,year,semester,session of [schedule] and update [isAcademicDetailsFilled] using the
    [academicController] information*/
    fun onAcademicInfoAddRequest()


    interface Validator {
        val areAllFieldsFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(
            courseCodeFlow: StateFlow<String>,
            teacherNameFlow: StateFlow<String>,
            startTimeFlow: StateFlow<String>,
            endTimeFlow: StateFlow<String>
        )

    }


}
