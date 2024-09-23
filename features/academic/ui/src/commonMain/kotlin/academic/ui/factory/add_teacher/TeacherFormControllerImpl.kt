package academic.ui.factory.add_teacher

import academic.ui.admin.add_teacher.TeacherFormController
import academic.ui.model.Dept
import academic.ui.model.TeacherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * Private implementation of the TeacherFormController interface.
 * Manages the state of TeacherModel using MutableStateFlow and responds to events.
 */
internal class TeacherFormControllerImpl(
    override val validator: TeacherFormController.Validator
) : TeacherFormController {
    private val _teacherState = MutableStateFlow(_emptyState())
    private val _departments = MutableStateFlow(
        listOf(
            Dept("Computer Science", "CS"),
            Dept("Mathematics", "Math"),
            Dept("Physics", "Phys"),
            Dept("Chemistry", "Chem"),
        )
    )
    override val dept = _departments.asStateFlow()

    override val teacherState: StateFlow<TeacherModel> = _teacherState.asStateFlow()
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

    override fun onDeptChange(value: Dept) {
        _teacherState.value = _teacherState.value.copy(dept = value)
    }

    override fun onRoomNoChange(value: Int) {
        _teacherState.value = _teacherState.value.copy(roomNo = value)
    }

    init {
        validator.observeFieldChanges(state = teacherState)
    }


    //TODO:Helper methods section
    private fun _emptyState() = TeacherModel(
        name = "",
        email = "",
        additionalEmail = "",
        achievements = "",
        phone = "",
        designations = "",
        dept = Dept("", ""),
        roomNo = 0
    )
}