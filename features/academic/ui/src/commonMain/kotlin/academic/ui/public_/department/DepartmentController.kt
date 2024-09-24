package academic.ui.public_.department

import academic.model.DepartmentModel
import kotlinx.coroutines.flow.StateFlow

internal interface DepartmentController {
    val isFetching: StateFlow<Boolean>
    val departments: StateFlow<List<DepartmentModel>>
    val selected: StateFlow<Int?>
    fun onSelected(index: Int)
    suspend fun fetchDepartments(facultyId:String)

}