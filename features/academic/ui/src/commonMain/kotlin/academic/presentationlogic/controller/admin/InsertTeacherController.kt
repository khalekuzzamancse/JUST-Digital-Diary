package academic.presentationlogic.controller.admin

import academic.presentationlogic.model.TeacherReadModel
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface that defines the contract for controlling the TeacherForm.
 * It manages the state of TeacherModel and handles events related to form inputs.
 *
 * @property teacherState A [StateFlow] that emits the current state of [TeacherReadModel].
 * @property onNameChange Handles the event when the teacher's name changes.
 * @property onEmailChange Handles the event when the teacher's email changes.
 * @property onAdditionalEmailChange Handles the event when the teacher's additional email changes.
 * @property onAchievementsChange Handles the event when the teacher's achievements change.
 * @property onPhoneChange Handles the event when the teacher's phone number changes.
 * @property onDesignationsChange Handles the event when the teacher's designations change.
 * @property onDeptChange Handles the event when the teacher's department changes.
 * @property onRoomNoChange Handles the event when the teacher's room number changes.
 */
internal interface InsertTeacherController :TeacherEntryController{
    suspend fun insert()
}
