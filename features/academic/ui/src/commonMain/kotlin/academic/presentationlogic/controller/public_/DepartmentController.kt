
package academic.presentationlogic.controller.public_

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.model.public_.DepartmentModel
import kotlinx.coroutines.flow.StateFlow
/**
 * - It child of [CoreController]
 */
internal interface DepartmentController: CoreController {
    val departments: StateFlow<List<DepartmentModel>>
    val selected: StateFlow<Int?>
    fun onSelected(index: Int)
    suspend fun fetchDepartments(facultyId:String)

}