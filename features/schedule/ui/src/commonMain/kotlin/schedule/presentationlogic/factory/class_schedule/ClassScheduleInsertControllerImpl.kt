@file:Suppress("functionName")

package schedule.presentationlogic.factory.class_schedule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

import schedule.domain.exception.CustomException
import schedule.domain.usecase.InsertCalenderUseCase
import schedule.presentationlogic.controller.ClassScheduleInsertController
import schedule.presentationlogic.controller.core.AcademicInfoController
import schedule.presentationlogic.controller.core.CoreControllerImpl
import schedule.presentationlogic.mapper.ModelMapper
import schedule.presentationlogic.model.ClassDetailModel
import schedule.presentationlogic.model.ClassScheduleModel


/**
 * - Properties are passed as function parameters because the instance will be created via a factory method
 *   If we were to pass them through the constructor, the factory would need to know the properties beforehand,
 *   which we want to avoid
 * - This class receives all dependencies via the constructor, making it easy to integrate
with Dependency Injection (DI)
 */
internal class ClassScheduleInsertControllerImpl internal constructor(
    override val validator: ClassScheduleInsertController.Validator,
    private val addCommand: AddCommand,
    override val academicController: AcademicInfoController,
    private val insertUseCase: InsertCalenderUseCase,
) : ClassScheduleInsertController, CoreControllerImpl() {

    private val _academicFormFilled = MutableStateFlow(false)
    private val _schedule = MutableStateFlow(toEmpty())
    private val _courseCode = MutableStateFlow("")
    private var deptId = ""//used to while inserting or updating to server or database

    private val _selectedDayIndex = MutableStateFlow(0)
    private val _teacherName = MutableStateFlow("")
    private val _startTime = MutableStateFlow("")
    private val _endTime = MutableStateFlow("")

    override val isAcademicDetailsFilled = _academicFormFilled.asStateFlow()

    override val schedule = _schedule.asStateFlow()
    override val courseCode: StateFlow<String> = _courseCode.asStateFlow()
    override val selectedDayIndex: StateFlow<Int> = _selectedDayIndex.asStateFlow()
    override val teacherName: StateFlow<String> = _teacherName.asStateFlow()
    override val startTime: StateFlow<String> = _startTime.asStateFlow()
    override val endTime: StateFlow<String> = _endTime.asStateFlow()


    init {
        validator.observeFieldChanges(
            courseCode, teacherName, startTime, endTime
        )
    }


    override fun onCourseCodeChanged(value: String) = _courseCode.update { value.uppercase() }
    override fun onSelectedDayIndexChanged(value: Int) = _selectedDayIndex.update { value }
    override fun onTeacherNameChanged(value: String) = _teacherName.update { value }
    override fun onStartTimeChanged(value: String) = _startTime.update { value }
    override fun onEndTimeChanged(value: String) = _endTime.update { value }
    override suspend fun insert() {
        super.startLoading()
        insertUseCase.execute(
            model = with(ModelMapper){schedule.value.toModel()},
            deptId = deptId
        ).fold(
            onSuccess = {
            },
            onFailure = { exception ->
                when (exception) {
                    is CustomException -> super.updateErrorMessage(exception.message)
                    else -> super.updateErrorMessage("Failed upload")
                }
            }
        )
        super.stopLoading()

    }


    override fun onAcademicInfoAddRequest() {
        val year = academicController.year.value
        val semester = academicController.semester.value
        val session = academicController.session.value
        val selectedDeptIndex = academicController.selectedDeptIndex.value
        var deptName = ""
        if (selectedDeptIndex != null) {
            val dept = academicController.department.value[selectedDeptIndex]
            deptName = dept.name + "(${dept.shortname})"
            deptId = dept.deptId
        }
        _schedule.update { schedule ->
            schedule.copy(
                year = year, semester = semester, session = session, deptName = deptName
            )

        }
        _academicFormFilled.update {
            _schedule._checkAcademicInfoFilled()
        }
    }

    override val isLoading: StateFlow<Boolean> = combine(
        academicController.isLoading,
        super._isLoading
    ) { academicFormLoading, thisControllerLoading ->
        academicFormLoading || thisControllerLoading
    }.stateIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    override val statusMessage = combine(
        academicController.statusMessage,
        super._statusMessage
    ) { academicControllerMsg, thisControllerMsg ->
        academicControllerMsg ?: thisControllerMsg
    }.stateIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    override fun addToSchedule(): Boolean {
        _schedule.update { schedule ->
            addCommand.execute(
                scheduleModel = schedule,
                day = days[selectedDayIndex.value],
                classDetail = ClassDetailModel(
                    courseCode = courseCode.value,
                    time = "${startTime.value}-${endTime.value}",
                    teacherName = teacherName.value,
                    durationInHours = 1
                )
            )
        }
        return true
    }

    //TODO:Helper method section
    private fun toEmpty() = ClassScheduleModel(
        deptName = "",
        session = "",
        year = "",
        semester = "",
        routine = emptyList()
    )

    private fun MutableStateFlow<ClassScheduleModel>._checkAcademicInfoFilled() =
        this.value.deptName.isNotBlank()
                && this.value.year.isNotBlank()
                && this.value.session.isNotBlank()
                && this.value.semester.isNotBlank()
}

