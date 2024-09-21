
import academic.ui.admin.Dept
import academic.ui.admin.TeacherFormController
import academic.ui.admin.TeacherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * Private implementation of the TeacherFormController interface.
 * Manages the state of TeacherModel using MutableStateFlow and responds to events.
 */
internal class TeacherFormControllerImpl : TeacherFormController {
    private val _teacherState = MutableStateFlow(
        TeacherModel(
            name = "",
            email = "",
            additionalEmail = "",
            achievements = "",
            phone = "",
            designations = "",
            dept = Dept("", ""),
            roomNo = 0
        )
    )

    // Public immutable StateFlow for observing the TeacherModel state
    override val teacherState: StateFlow<TeacherModel> = _teacherState.asStateFlow()

    override fun onNameChange(newName: String) {
        _teacherState.value = _teacherState.value.copy(name = newName)
    }

    override fun onEmailChange(newEmail: String) {
        _teacherState.value = _teacherState.value.copy(email = newEmail)
    }

    override fun onAdditionalEmailChange(newAdditionalEmail: String) {
        _teacherState.value = _teacherState.value.copy(additionalEmail = newAdditionalEmail)
    }

    override fun onAchievementsChange(newAchievements: String) {
        _teacherState.value = _teacherState.value.copy(achievements = newAchievements)
    }

    override fun onPhoneChange(newPhone: String) {
        _teacherState.value = _teacherState.value.copy(phone = newPhone)
    }

    override fun onDesignationsChange(newDesignations: String) {
        _teacherState.value = _teacherState.value.copy(designations = newDesignations)
    }

    override fun onDeptChange(newDept: Dept) {
        _teacherState.value = _teacherState.value.copy(dept = newDept)
    }

    override fun onRoomNoChange(newRoomNo: Int) {
        _teacherState.value = _teacherState.value.copy(roomNo = newRoomNo)
    }
}