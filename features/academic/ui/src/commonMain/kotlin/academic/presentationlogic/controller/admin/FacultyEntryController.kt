package academic.presentationlogic.controller.admin

import academic.presentationlogic.model.admin.FacultyEntryModel
import kotlinx.coroutines.flow.StateFlow

internal interface FacultyEntryController {
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

    //same can be used as for addFaculty or update existing faculty,that is why giving default implementation
    suspend fun onAddRequest() {
        TODO("If you are adder then implement this method")
    }

    //same can be used as for addFaculty or update existing faculty,that is why giving default implementation
    suspend fun onUpdateRequest() {
        TODO("If you are updater then implement this method")
    }


    interface Validator {
        /**
         * Indicates whether all mandatory fields are filled.
         * Avoids using names tied to specific UI elements or frameworks (e.g., "enableButton")
         * to ensure this property remains independent of any specific implementation.
         */
        val areMandatoryFieldFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(state: StateFlow<FacultyEntryModel>)

    }
}
