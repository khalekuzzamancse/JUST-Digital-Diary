
package academic.presentationlogic.controller.public_

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.model.DepartmentReadModel
import kotlinx.coroutines.flow.StateFlow
/**
 * - It child of [CoreController]
 */
internal interface DepartmentController: CoreController {
    val departments: StateFlow<List<DepartmentReadModel>>
    val selected: StateFlow<Int?>
    fun onSelected(index: Int)
    suspend fun fetchDepartments(facultyId:String)

}