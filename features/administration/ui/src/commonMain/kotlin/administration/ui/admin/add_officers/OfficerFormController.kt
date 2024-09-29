package administration.ui.admin.add_officers

import administration.controller_presenter.model.AdminOfficerModel
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface that defines the contract for controlling the TeacherForm.
 * It manages the state of TeacherModel and handles events related to form inputs.
 *
 * @property state A [StateFlow] that emits the current state of [AdminOfficerModel].
 * @property onNameChange Handles the event when the teacher's name changes.
 * @property onEmailChange Handles the event when the teacher's email changes.
 * @property onAdditionalEmailChange Handles the event when the teacher's additional email changes.
 * @property onAchievementsChange Handles the event when the teacher's achievements change.
 * @property onPhoneChange Handles the event when the teacher's phone number changes.
 * @property onDesignationsChange Handles the event when the teacher's designations change.
 * @property onRoomNoChange Handles the event when the teacher's room number changes.
 */
interface OfficerFormController {
    val state: StateFlow<AdminOfficerModel>
    val validator: Validator
    fun onNameChange(value: String)
    fun onEmailChange(value: String)
    fun onAdditionalEmailChange(value: String)
    fun onAchievementsChange(value: String)
    fun onPhoneChange(value: String)
    fun onDesignationsChange(value: String)
    fun onRoomNoChange(value: Int)

    interface Validator {
        val areMandatoryFieldFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(state: StateFlow<AdminOfficerModel>)

    }
}
