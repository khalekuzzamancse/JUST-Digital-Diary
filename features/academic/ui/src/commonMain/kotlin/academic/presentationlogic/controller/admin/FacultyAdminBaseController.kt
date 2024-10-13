package academic.presentationlogic.controller.admin

import academic.presentationlogic.model.admin.FacultyEntryModel
import kotlinx.coroutines.flow.StateFlow

/**
 * - The common part that can be used for both insert and update
 */
internal interface FacultyAdminBaseController {
    /**
     * Indicates whether a network operation is currently in progress.
     * Uses a name that is independent of any UI framework, ensuring that this layer remains framework-agnostic,
     * Based on this state the  UI can do something such as show Loading state using UI elements or disable something , etc
     */
    val networkIOInProgress: StateFlow<Boolean>
    val validator: Validator

    /**
     * Represents the current state of the faculty being added.
     * Uses a model that is decoupled from any specific UI implementation to maintain abstraction.
     */
    val faculty: StateFlow<FacultyEntryModel>

    /**
     * A message indicating the status of the operation (success or failure).
     * Named in a way that is independent of any UI concerns to ensure framework-independence.
     */
    val statusMessage: StateFlow<String?>
    fun onPriorityChanged(value: String)
    fun onNameChanged(value: String)


    interface Validator {
        /**
         * Indicates whether all mandatory fields are filled.
         * Avoids using names tied to specific UI elements or frameworks (e.g., "enableButton")
         * to ensure this property remains independent of any specific implementation.
         */
        val areMandatoryFieldFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun activate(state: StateFlow<FacultyEntryModel>)
    }
}
