package academic.ui.public_.department

import academic.model.DepartmentModel
import academic.model.FacultyModel
import kotlinx.coroutines.flow.StateFlow

internal interface DepartmentController {
    val isLoading: StateFlow<Boolean>
    val departments: StateFlow<List<DepartmentModel>>
    val selected: StateFlow<Int?>
    fun onSelected(index: Int)
}