package schedule.presentationlogic.factory.exam_schedule

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import schedule.presentationlogic.model.ExamDetailsModel
import schedule.presentationlogic.model.ExamScheduleModel
import schedule.presentationlogic.controller.ExamScheduleInsertController


/**
 * - Properties are passed as function parameters because the instance will be created via a factory method
 *   If we were to pass them through the constructor, the factory would need to know the properties beforehand,
 *   which we want to avoid
 * - This class receives all dependencies via the constructor, making it easy to integrate
with Dependency Injection (DI)
 */
class ExamScheduleControllerImpl internal constructor(
    override val validator: ExamScheduleInsertController.Validator,
    private val addCommand: AddCommand,
) : ExamScheduleInsertController {
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


    private val _courseTitle = MutableStateFlow("")
    override val courseTitle: StateFlow<String> = _courseTitle.asStateFlow()

    private val _time = MutableStateFlow("")
    override val time: StateFlow<String> = _time.asStateFlow()

    private val _date = MutableStateFlow("")
    override val date: StateFlow<String> = _date.asStateFlow()

    init {
        validator.observeFieldChanges(
            year = year,
            semester = semester,
            session = session,
            courseCode = courseCode,
            courseTitle = courseTitle,
            time = time,
            date = date
        )
    }


    //TODO: Event handler
    override fun onSessionChanged(value: String) {
        _state.update { it.copy(session = value) }
        _session.update { value }
    }

    override fun onYearChanged(value: String) {
        _state.update { it.copy(year = value) }
        _year.update { value }
    }

    override fun onSemesterChanged(value: String) {
        _state.update { it.copy(semester = value) }
        _semester.update { value }
    }

    override fun onCourseCodeChanged(value: String) = _courseCode.update { value.uppercase() }
    override fun onCourseTitleChanged(value: String) = _courseTitle.update { value }
    override fun onTimeChanged(value: String) = _time.update { value }
    override fun onDateChanged(value: String) = _date.update { value }


    override fun onCourseAddRequest(): Boolean {
        _state.update { schedule ->
            addCommand.execute(
                schedule = schedule,
                exam = ExamDetailsModel(
                    courseCode = courseCode.value,
                    time = time.value,
                    courseTitle = courseTitle.value,
                    date = date.value
                )
            )
        }
        return true
    }

    //TODO helper method section

    private fun toEmpty() = ExamScheduleModel(
        dept = "",
        session = "",
        year = "",
        semester = "",
        exams = emptyList()
    )

}

