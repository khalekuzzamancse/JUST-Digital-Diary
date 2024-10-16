@file:Suppress("unused")
package schedule.presentationlogic.controller.core

import kotlinx.coroutines.flow.StateFlow
import schedule.presentationlogic.model.DepartmentModel

/**Recommend to use separate UI components for academic info because this is one time event*/
interface AcademicInfoController :CoreController{
    /**List of department that help the user to select under which department the schedule belongs to */
    val department: StateFlow<List<DepartmentModel>>

    /** Helps display the department selected by the user. Since the user already has a list of departments,
     * it's sufficient to use the index instead of the department name. As this layer is not concerned with the UI framework,
     * the index will be more helpful if The UI uses a dropdown-like component for this selection.
     */
    val selectedDeptIndex: StateFlow<Int?>
    val year: StateFlow<String>
    val semester: StateFlow<String>
    val session: StateFlow<String>
    val validator: Validator

    fun onDeptSelected(index: Int)
    fun onYearChanged(value:String)
    fun onSessionChanged(value:String)
    fun onSemesterChanged(value:String)
    interface Validator {
        val areAllFieldsFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(
            year: StateFlow<String>,
            semester: StateFlow<String>,
            session: StateFlow<String>,
            selectedDeptIndex:StateFlow<Int?>
        )
    }

}