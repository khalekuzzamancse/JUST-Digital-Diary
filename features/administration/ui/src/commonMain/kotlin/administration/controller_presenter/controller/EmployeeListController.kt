package administration.controller_presenter.controller


import administration.controller_presenter.model.AdminOfficerModel
import kotlinx.coroutines.flow.StateFlow

internal interface EmployeeListController {
    val isFetching: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val employees: StateFlow<List<AdminOfficerModel>>
    suspend fun fetch(subOfficeId: String)
}
