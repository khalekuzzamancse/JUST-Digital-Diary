package academic.presentationlogic.controller.public_

import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.model.public_.TeacherModel
import kotlinx.coroutines.flow.StateFlow
/**
 * - It child of [CoreController]
 */
internal interface TeachersController: CoreController {
    val teachers: StateFlow<List<TeacherModel>>
    suspend fun fetch(deptId: String)
}
