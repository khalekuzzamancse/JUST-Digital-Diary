package academic.presentationlogic.controller.public_

import academic.presentationlogic.model.public_.DepartmentModel
import kotlinx.coroutines.flow.StateFlow

internal interface DepartmentController {
    val isFetching: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val departments: StateFlow<List<DepartmentModel>>
    val selected: StateFlow<Int?>

    fun onSelected(index: Int)
    suspend fun fetchDepartments(facultyId:String)

}