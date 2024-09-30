package academic.controller_presenter.controller

import academic.controller_presenter.model.TeacherModel
import kotlinx.coroutines.flow.StateFlow

internal interface TeachersController {
    val isFetching: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val teachers: StateFlow<List<TeacherModel>>
    suspend fun fetch(deptId: String)
}
