package academic.controller_presenter.controller

import academic.controller_presenter.model.DepartmentModel
import kotlinx.coroutines.flow.StateFlow

internal interface DepartmentController {
    val isFetching: StateFlow<Boolean>
    val departments: StateFlow<List<DepartmentModel>>
    val selected: StateFlow<Int?>
    fun onSelected(index: Int)
    suspend fun fetchDepartments(facultyId:String)

}