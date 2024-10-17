package academic.presentationlogic.controller.admin

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.model.FacultyWriteModel
import kotlinx.coroutines.flow.StateFlow

/**
 * - The common part that can be used for both insert and update
 * - It child of [CoreController]
 */
internal interface FacultyEntryController : CoreController {
    val validator: Validator

    /**
     * Represents the current state of the faculty being added.
     * Uses a model that is decoupled from any specific UI implementation to maintain abstraction.
     */
    val faculty: StateFlow<FacultyWriteModel>
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
        fun activate(state: StateFlow<FacultyWriteModel>)
    }
}
