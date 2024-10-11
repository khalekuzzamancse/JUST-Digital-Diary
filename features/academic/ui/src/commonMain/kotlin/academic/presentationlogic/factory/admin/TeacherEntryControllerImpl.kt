package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.model.admin.TeacherEntryModel
import academic.presentationlogic.model.public_.Dept
import academic.presentationlogic.model.public_.FacultyModel
import academic.presentationlogic.model.public_.TeacherModel
import faculty.domain.model.public_.DepartmentModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/**
 * Private implementation of the TeacherFormController interface.
 * Manages the state of TeacherModel using MutableStateFlow and responds to events.
 */
internal class TeacherEntryControllerImpl(
    override val validator: TeacherEntryController.Validator
) : TeacherEntryController {
    private val _networkIOInProgress = MutableStateFlow(false)
    private val _statusMessage = MutableStateFlow<String?>(null)
    private val _teacherState = MutableStateFlow(_emptyState())
    private val _departments = MutableStateFlow(_dummyDept())
    private val _selectedDeptIndex=MutableStateFlow<Int?>(null)



    override val statusMessage = _statusMessage.asStateFlow()
    override val dept = _departments.asStateFlow()
    override val selectedDeptIndex=_selectedDeptIndex.asStateFlow()

    override val networkIOInProgress = _networkIOInProgress.asStateFlow()
    override val teacherState: StateFlow<TeacherEntryModel> = _teacherState.asStateFlow()
    override fun onNameChange(value: String) {
        _teacherState.value = _teacherState.value.copy(name = value)
    }

    override fun onEmailChange(value: String) {
        _teacherState.value = _teacherState.value.copy(email = value)
    }

    override fun onAdditionalEmailChange(value: String) {
        _teacherState.value = _teacherState.value.copy(additionalEmail = value)
    }

    override fun onAchievementsChange(value: String) {
        _teacherState.value = _teacherState.value.copy(achievements = value)
    }

    override fun onPhoneChange(value: String) {
        _teacherState.value = _teacherState.value.copy(phone = value)
    }

    override fun onDesignationsChange(value: String) {
        _teacherState.value = _teacherState.value.copy(designations = value)
    }

    override fun onDeptChange(index: Int) {
        _selectedDeptIndex.update { index }
        try {//Since accessing index, taking double safety
            _teacherState.update { it.copy(deptId = dept.value[index].deptId) }
        } catch (_: Exception) {

        }
      //  _teacherState.value = _teacherState.value.copy(dept = value)
    }

    override fun onRoomNoChange(value: String) {
        _teacherState.value = _teacherState.value.copy(roomNo = value)
    }

    override fun onIdChange(value: String) {
        _teacherState.value = _teacherState.value.copy(id = value)
    }

    init {
        validator.observeFieldChanges(state = teacherState)
    }


    //TODO:Helper methods section
    private fun _emptyState() = TeacherEntryModel(
        name = "",
        email = "",
        additionalEmail = "",
        achievements = "",
        phone = "",
        deptId = "",
        roomNo ="",
        designations = "",
        id = ""
    )
    private fun _dummyDept()=listOf(
        DepartmentModel(name = "Computer Science", shortName = "CS", deptId = "1", employeeCount = 0),
        DepartmentModel(name = "Mathematics", shortName = "Math", deptId = "2", employeeCount = 0),
        DepartmentModel(name = "Physics", shortName = "Phys", deptId = "3", employeeCount = 0),
        DepartmentModel(name = "Chemistry", shortName = "Chem", deptId = "4", employeeCount = 0)
    )
}