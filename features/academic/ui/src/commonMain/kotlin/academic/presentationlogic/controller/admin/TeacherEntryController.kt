package academic.presentationlogic.controller.admin

import academic.presentationlogic.model.admin.TeacherEntryModel
import academic.presentationlogic.model.public_.DepartmentModel
import academic.presentationlogic.model.public_.TeacherModel
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
internal interface TeacherEntryController {
    val teacherState: StateFlow<TeacherEntryModel>
    val validator: Validator
    /**
     * Indicates whether a network operation is currently in progress.
     * Uses a name that is independent of any UI framework, ensuring that this layer remains framework-agnostic,
     * Based on this state the  UI can do something such as show Loading state using UI elements or disable something , etc
     */
    val networkIOInProgress: StateFlow<Boolean>

    /**
     * A message indicating the status of the operation (success or failure).
     * Named in a way that is independent of any UI concerns to ensure framework-independence.
     */
    val statusMessage: StateFlow<String?>

    /**
     * -Help to admin, under which   department is going to add
     *- The `Implementer` should fetch the faculty list
     **/
    val dept:StateFlow<List<DepartmentModel>>

    /**
     * - Since UI has access teh dept list so need to store the selected dept instead index is enough,
     * the UI can figure out the faculty by this index,
     * This will help(not force) to UI to implement a dropdown,but since this layer is UI independent,so UI can use
     * other UI components also by getting the selected faculty by index
     */
    val selectedDeptIndex: StateFlow<Int?>

    fun onNameChange(value: String)
    fun onEmailChange(value: String)
    fun onAdditionalEmailChange(value: String)
    fun onAchievementsChange(value: String)
    fun onPhoneChange(value: String)
    fun onDesignationsChange(value: String)
    fun onDeptChange(index: Int)
    fun onRoomNoChange(value: String)
    fun onIdChange(value: String)

    interface Validator {
        val areMandatoryFieldFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(state: StateFlow<TeacherEntryModel>)

    }
}
