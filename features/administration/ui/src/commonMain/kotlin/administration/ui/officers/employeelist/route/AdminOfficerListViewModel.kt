package administration.ui.officers.employeelist.route

import admin_office.domain.officers.repoisitory.AdminOfficerListRepository
import administration.ui.officers.employeelist.components.AdminOfficer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AdminOfficerListViewModel(
    private val repository: AdminOfficerListRepository,
    private val subOfficeId: String,
) {
    private val _uiState = MutableStateFlow(AdminOfficerListDestinationState())
    val uiState = _uiState.asStateFlow()
    suspend fun updateTeacherList() {
        startLoading()
        val result = repository.getOfficers(subOfficeId)
        if (result.isSuccess) {
            _uiState.update { uiState ->
                val adminOfficerListState =
                    uiState.adminOfficerListState.copy(adminOfficers = result.getOrDefault(emptyList()).map { model ->
                        AdminOfficer(
                            name = model.name,
                            email = model.email,
                            additionalEmail = model.additionalEmail,
                            phone = model.phone ?: "",
                            achievements = model.achievements,
                            designations = model.designations,
                            roomNo = model.roomNo
                        )
                    })
                uiState.copy(
                    adminOfficerListState = adminOfficerListState
                )

            }
        }

        stopLoading()


    }

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }
}