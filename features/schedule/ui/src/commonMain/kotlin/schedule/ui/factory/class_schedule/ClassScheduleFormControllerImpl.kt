package schedule.ui.factory.class_schedule

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import schedule.ui.admin.ClassScheduleFormController
import schedule.ui.model.ClassDetailModel
import schedule.ui.model.ClassScheduleModel


/**
 * - The controller delegates the responsibility of adding a new class to the schedule to this command
 *   As a result, the controller's responsibility is reduced, and its code remains concise
 *  - Additionally, implementing an undo operation in the future will be easier through this command
 */

interface AddCommand {
    fun execute(
        scheduleModel: ClassScheduleModel,
        day: String,
        classDetail: ClassDetailModel
    ): ClassScheduleModel
}
/**
 * - Properties are passed as function parameters because the instance will be created via a factory method
 *   If we were to pass them through the constructor, the factory would need to know the properties beforehand,
 *   which we want to avoid
 * - This class receives all dependencies via the constructor, making it easy to integrate
with Dependency Injection (DI)
 */
class ClassScheduleFormControllerImpl internal constructor(
    override val validator: ClassScheduleFormController.Validator,
    private val addCommand: AddCommand,
) : ClassScheduleFormController {
    private val _state = MutableStateFlow(toEmpty())
    override val state = _state.asStateFlow()

    private val _courseCode = MutableStateFlow("")
    override val courseCode: StateFlow<String> = _courseCode.asStateFlow()

    private val _year = MutableStateFlow("")
    override val year: StateFlow<String> = _year.asStateFlow()

    private val _semester = MutableStateFlow("")
    override val semester: StateFlow<String> = _semester.asStateFlow()

    private val _session = MutableStateFlow("")
    override val session: StateFlow<String> = _session.asStateFlow()


    private val _selectedDayIndex = MutableStateFlow(0)
    override val selectedDayIndex: StateFlow<Int> = _selectedDayIndex.asStateFlow()

    private val _teacherName = MutableStateFlow("")
    override val teacherName: StateFlow<String> = _teacherName.asStateFlow()

    private val _startTime = MutableStateFlow("")
    override val startTime: StateFlow<String> = _startTime.asStateFlow()

    private val _endTime = MutableStateFlow("")
    override val endTime: StateFlow<String> = _endTime.asStateFlow()

    init {
        validator.observeFieldChanges(
            courseCode, year, semester,
            session, teacherName, startTime, endTime
        )
    }


    //TODO: Event handler
    override fun onSessionChanged(value: String) = _session.update { value }
    override fun onYearChanged(value: String) = _year.update { value }
    override fun onSemesterChanged(value: String) = _semester.update { value }
    override fun onCourseCodeChanged(value: String) = _courseCode.update { value }
    override fun onSelectedDayIndexChanged(value: Int) = _selectedDayIndex.update { value }
    override fun onTeacherNameChanged(value: String) = _teacherName.update { value }
    override fun onStartTimeChanged(value: String) = _startTime.update { value }
    override fun onEndTimeChanged(value: String) = _endTime.update { value }


    override fun onCourseAddRequest(): Boolean {
        _state.update { schedule ->
            addCommand.execute(
                scheduleModel = schedule,
                day = days[selectedDayIndex.value],
                classDetail = ClassDetailModel(
                    courseCode = courseCode.value,
                    time = "${startTime.value}-${endTime.value}",
                    teacherShortName = teacherName.value,
                    durationInHours = 1
                )
            )
        }
        return true
    }

    //TODO helper method section

    private fun toEmpty() = ClassScheduleModel(
        dept = "",
        session = "",
        year = "",
        semester = "",
        routine = emptyList()
    )

}

