package academic.presentationlogic.controller.admin

import academic.presentationlogic.model.admin.DepartmentEntryModel
import academic.presentationlogic.model.public_.FacultyModel
import kotlinx.coroutines.flow.StateFlow

internal interface DepartmentEntryController {
    /**
     * Indicates whether a network operation is currently in progress.
     * Uses a name that is independent of any UI framework, ensuring that this layer remains framework-agnostic,
     * Based on this state the  UI can do something such as show Loading state using UI elements or disable something , etc
     */
    val networkIOInProgress: StateFlow<Boolean>
    val validator: Validator

    val dept: StateFlow<DepartmentEntryModel>

    /**- Help to admin, under which faculty the department is going to add
     *- The `Implementer` should fetch the faculty list
     **/
    val faculties: StateFlow<List<FacultyModel>>

    /**
     * - Since UI has access teh faculty list so need to store the selected faculty instead index is enough,
     * the UI can figure out the faculty by this index,
     * This will help(not force) to UI to implement a dropdown,but since this layer is UI independent,so UI can use
     * other UI components also by getting the selected faculty by index
     */
    val selectedFacultyIndex: StateFlow<Int?>

    /**
     * A message indicating the status of the operation (success or failure).
     * Named in a way that is independent of any UI concerns to ensure framework-independence.
     */
    val statusMessage: StateFlow<String?>
    fun onNameChanged(value: String)
    fun onShortNameChanged(value: String)
    fun onPriorityChanged(value: String)
    fun onFacultySelected(index: Int)


    //same can be used as for add or update existing ,that is why giving default implementation
    suspend fun onAddRequest() {
        TODO("If you are adder then implement this method")
    }

    //same can be used as for add or update existing ,that is why giving default implementation
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
        fun observeFieldChanges(state: StateFlow<DepartmentEntryModel>)

    }
}
