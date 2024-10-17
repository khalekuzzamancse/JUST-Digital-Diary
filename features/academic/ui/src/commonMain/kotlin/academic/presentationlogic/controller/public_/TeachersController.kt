package academic.presentationlogic.controller.public_

import academic.presentationlogic.controller.core.ICoreController
import academic.presentationlogic.model.TeacherReadModel
import kotlinx.coroutines.flow.StateFlow
/**
 * - It child of [ICoreController]
 */
internal interface TeachersController: ICoreController {
    val teachers: StateFlow<List<TeacherReadModel>>
    suspend fun fetch(deptId: String)
}
