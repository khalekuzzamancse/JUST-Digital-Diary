package academic.ui.public_.teachers

import academic.model.TeacherModel
import kotlinx.coroutines.flow.StateFlow

internal interface TeachersController {
    val isFetching: StateFlow<Boolean>
    val teachers: StateFlow<List<TeacherModel>>
    suspend fun fetch(deptId: String)
}
