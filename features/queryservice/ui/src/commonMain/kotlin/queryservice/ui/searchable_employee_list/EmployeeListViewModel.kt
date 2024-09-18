package queryservice.ui.searchable_employee_list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import queryservice.domain.model.EmployeeModel
import queryservice.domain.repoisitory.EmployeesRepository

class EmployeeListViewModel(
    private val repository: EmployeesRepository,
) {
    private val _uiState = MutableStateFlow(EmployeeListDestinationState())
    val uiState = _uiState.asStateFlow()

    suspend fun loadTeacherList() {
        val result = repository.getEmployees()
        if (result.isSuccess) {
            _uiState.update { uiState ->
                uiState.copy(
                    employee = toEmployees(result.getOrDefault(emptyList()))
                )
            }
        }
    }

    private fun toEmployees(result: List<EmployeeModel>): List<Employee> {
        return result.map { model ->
            Employee(
                name = model.name,
                email = model.email,
                additionalEmail = model.additionalEmail,
                phone = model.phone,
                achievements = model.achievements,
                designations = model.designations,
                roomNo = model.roomNo
            )
        }
    }

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

}




