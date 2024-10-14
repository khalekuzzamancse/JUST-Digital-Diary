package academic.presentationlogic.controller.admin

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.model.admin.DepartmentEntryModel
import academic.presentationlogic.model.public_.FacultyModel
import kotlinx.coroutines.flow.StateFlow

/**
 * - The common part that can be used for both insert and update
 * - It child of [CoreController]
 */
internal interface DeptEntryController : CoreController {
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
    fun onNameChanged(value: String)
    fun onShortNameChanged(value: String)
    fun onPriorityChanged(value: String)
    fun onFacultySelected(index: Int)

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
