package academic.presentationlogic.controller.public_

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.model.FacultyReadModel
import kotlinx.coroutines.flow.StateFlow
/**
 * - It child of [CoreController]
 */
internal interface FacultyController : CoreController {
    val faculties: StateFlow<List<FacultyReadModel>>
    val selected: StateFlow<Int?>
    suspend fun fetchFaculty()
    fun onSelected(index: Int?)

}