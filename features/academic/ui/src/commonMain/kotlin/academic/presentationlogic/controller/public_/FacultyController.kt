package academic.presentationlogic.controller.public_

import academic.presentationlogic.controller.core.ICoreController
import academic.presentationlogic.model.FacultyReadModel
import kotlinx.coroutines.flow.StateFlow
/**
 * - It child of [ICoreController]
 */
internal interface FacultyController : ICoreController {
    val faculties: StateFlow<List<FacultyReadModel>>
    val selected: StateFlow<Int?>
    suspend fun readFaculties()

    /**Should be used  for pull to refresh or read again after deleting*/
    suspend fun refresh()
    fun onSelected(index: Int?)

}