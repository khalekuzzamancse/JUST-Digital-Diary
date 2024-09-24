package academic.ui.public_.faculty

import academic.model.FacultyModel
import kotlinx.coroutines.flow.StateFlow

internal interface FacultyController {
    val isLoading: StateFlow<Boolean>
    val faculties: StateFlow<List<FacultyModel>>
    val selected: StateFlow<Int?>
    fun onSelected(index: Int?)
}