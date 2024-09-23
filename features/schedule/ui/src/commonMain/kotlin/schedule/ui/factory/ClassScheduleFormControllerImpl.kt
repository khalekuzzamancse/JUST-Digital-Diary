package schedule.ui.factory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import schedule.ui.admin.ClassScheduleFormController
import schedule.ui.model.ClassDetailModel
import schedule.ui.model.ClassScheduleModel
import schedule.ui.model.DailyClassScheduleModel


class ClassScheduleFormControllerImpl : ClassScheduleFormController {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _areAllFieldsFilled = MutableStateFlow(false)
    override val areAllFieldsFilled: StateFlow<Boolean> = _areAllFieldsFilled.asStateFlow()
    private val _validationError = MutableStateFlow<String?>(null)

    override val validationError = _validationError.asStateFlow()
    private val _validator = InputValidator()

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


    //TODO: Event handler
    override fun onSessionChanged(value: String) = _session.update { value }
    override fun onYearChanged(value: String) = _year.update { value }
    override fun onSemesterChanged(value: String) = _semester.update { value }
    override fun onCourseCodeChanged(value: String) = _courseCode.update { value }
    override fun onSelectedDayIndexChanged(value: Int) = _selectedDayIndex.update { value }
    override fun onTeacherNameChanged(value: String) = _teacherName.update { value }
    override fun onStartTimeChanged(newStartTime: String) = _startTime.update { newStartTime }
    override fun onEndTimeChanged(newEndTime: String) = _endTime.update { newEndTime }


    override fun onCourseAddRequest(): Boolean {
        //validate start and end time
        if (!_validator.validateTime(_startTime.value)) {
            return false
        }
        //validate start and end time
        if (!_validator.validateTime(_endTime.value)) {
            return false
        }
        _validationError.update { null }



        _state.update { schedule ->
            AddCommand(
                scheduleModel = schedule,
                day = days[selectedDayIndex.value],
                classDetail = ClassDetailModel(
                    courseCode = courseCode.value,
                    time = "${startTime.value}-${endTime.value}",
                    teacherShortName = teacherName.value,
                    durationInHours = 1
                )
            ).execute()
        }
        return true
    }


    init {
        observeFields()
    }


    private fun observeFields() {
        combine(session, year, semester, courseCode, teacherName, startTime, endTime)
        { fields: Array<String> ->
            val year = fields[1]
            val semester = fields[2]
            val startTime = fields[5]
            val endTime = fields[6]

            var errorMessage = ""

            if (year.isNotBlank() && !_validator.validateYear(year))
                errorMessage += "Valid year range 1-5\n"
            if (year.isNotBlank() && !_validator.validateSemester(semester))
                errorMessage += "Valid semester range 1-10\n"
            //TODO: validate semester based on year such if year=4 then semester should 7 or 8


            if (startTime.isNotBlank() && !_validator.validateTime(startTime))
                errorMessage += "Valid time format dd:dd\n"
            if (endTime.isNotBlank() && !_validator.validateTime(endTime))
                errorMessage += "Valid time format dd:dd\n"

            if (startTime.isNotBlank() && startTime == endTime)
                errorMessage += "Start and end time must be different\n"
            if (errorMessage.isNotBlank())
                _validationError.update { errorMessage }
            else
                _validationError.update { null }
            fields.any { it.isNotBlank() } //all field are filled or not
        }.onEach { allFilled ->
            _areAllFieldsFilled.value
        }.launchIn(scope)
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

class AddCommand(
    private val scheduleModel: ClassScheduleModel,
    private val day: String,
    private val classDetail: ClassDetailModel
) {

    fun execute(): ClassScheduleModel {
        return if (isDayExisting()) {
            updateExistingDay()
        } else {
            addNewDay()
        }
    }

    // Check if the day exists in the current routine
    private fun isDayExisting(): Boolean {
        return scheduleModel.routine.any { it.day == day }
    }

    // Update the schedule for an existing day
    private fun updateExistingDay(): ClassScheduleModel {
        val updatedRoutine = scheduleModel.routine.map { dailyClassSchedule ->
            if (dailyClassSchedule.day == day) {
                dailyClassSchedule.copy(items = addClassToDay(dailyClassSchedule.items))
            } else {
                dailyClassSchedule
            }
        }
        return scheduleModel.copy(routine = updatedRoutine)
    }

    // Add a new day with the class detail if the day doesn't exist
    private fun addNewDay(): ClassScheduleModel {
        val newDailySchedule = DailyClassScheduleModel(day, mutableListOf(classDetail))
        val updatedRoutine = scheduleModel.routine.toMutableList().apply {
            add(newDailySchedule)
        }
        return scheduleModel.copy(routine = updatedRoutine)
    }

    // Helper method to add the new class detail to an existing day's items
    private fun addClassToDay(existingItems: List<ClassDetailModel>): List<ClassDetailModel> {
        val updatedItems = existingItems.toMutableList()
        updatedItems.add(classDetail)
        return updatedItems
    }
}
