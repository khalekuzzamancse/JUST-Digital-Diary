package academic.controller_presenter.controller

import academic.controller_presenter.model.Dept
import academic.controller_presenter.model.TeacherModel
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface that defines the contract for controlling the TeacherForm.
 * It manages the state of TeacherModel and handles events related to form inputs.
 *
 * @property teacherState A [StateFlow] that emits the current state of [TeacherModel].
 * @property onNameChange Handles the event when the teacher's name changes.
 * @property onEmailChange Handles the event when the teacher's email changes.
 * @property onAdditionalEmailChange Handles the event when the teacher's additional email changes.
 * @property onAchievementsChange Handles the event when the teacher's achievements change.
 * @property onPhoneChange Handles the event when the teacher's phone number changes.
 * @property onDesignationsChange Handles the event when the teacher's designations change.
 * @property onDeptChange Handles the event when the teacher's department changes.
 * @property onRoomNoChange Handles the event when the teacher's room number changes.
 */
internal interface TeacherFormController {
    val teacherState: StateFlow<TeacherModel>
    val validator: Validator
    val dept:StateFlow<List<Dept>>
    fun onNameChange(value: String)
    fun onEmailChange(value: String)
    fun onAdditionalEmailChange(value: String)
    fun onAchievementsChange(value: String)
    fun onPhoneChange(value: String)
    fun onDesignationsChange(value: String)
    fun onDeptChange(value: Dept)
    fun onRoomNoChange(value: Int)

    interface Validator {
        val areMandatoryFieldFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(state: StateFlow<TeacherModel>)

    }
}
