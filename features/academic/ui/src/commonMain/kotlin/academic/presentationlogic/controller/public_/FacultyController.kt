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
    suspend fun fetchFaculty()
    fun onSelected(index: Int?)

}