
package academic.presentationlogic.controller.public_

import academic.presentationlogic.controller.core.ICoreController
import academic.presentationlogic.model.DepartmentReadModel
import kotlinx.coroutines.flow.StateFlow
/**
 * - It child of [ICoreController]
 */
internal interface DepartmentController: ICoreController {
    val departments: StateFlow<List<DepartmentReadModel>>
    val selected: StateFlow<Int?>
    fun onSelected(index: Int)
    fun clearSelection()
    suspend fun readDepartments(facultyId:String)
    /**Should be used  for pull to refresh or read again after deleting*/
    suspend fun refresh()

}