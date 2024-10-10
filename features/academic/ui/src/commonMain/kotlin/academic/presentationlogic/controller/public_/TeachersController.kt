package academic.presentationlogic.controller.public_

import academic.presentationlogic.model.public_.TeacherModel
import kotlinx.coroutines.flow.StateFlow

internal interface TeachersController {
    val isFetching: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val teachers: StateFlow<List<TeacherModel>>
    suspend fun fetch(deptId: String)
}
