package administration.navgraph

import admin_office.domain.repository.AdminOfficeListRepository
import admin_office.domain.repository.SubOfficeListRepository
import administration.controller_presenter.model.OfficeModel
import administration.controller_presenter.model.SubOfficeModel
import administration.ui.suboffice.SubOfficeListState
import common.newui.CustomSnackBarData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AdminOfficeListViewModel(
    private val repository: AdminOfficeListRepository,
    private val subOfficeListRepository: SubOfficeListRepository
) {

    private val _uiState = MutableStateFlow(AdminOfficesDestinationState())
    val uiState = _uiState.asStateFlow()


    suspend fun loadOffices() {
        startLoading()
        val result = repository.getAdminOffices()
        if (result.isSuccess) {
            onFacultyListFetchedSuccessfully(result.getOrDefault(emptyList()).map {
                OfficeModel(
                    name = it.name,
                    id = it.officeId,
                    numberOfSubOffices = "${it.subOfficeCount}"
                )
            })
        } else {
            onFacultyListFetchedFailed(result.exceptionOrNull()?.message)

        }

        stopLoading()

    }

    private fun onFacultyListFetchedSuccessfully(faculties: List<OfficeModel>) {
        _uiState.update { state ->
            val facultyListState = state.officeState.copy(offices = faculties)
            state.copy(officeState = facultyListState)
        }

    }

    fun dismissSubOfficesDestination() {
        _uiState.update { state ->
            state.copy(subOfficeState = null, showSubOfficeList = false)
        }
    }

    private fun onFacultyListFetchedFailed(reason: String?) {
        updateSnackBarMessage(message = "Failed to load", details = "$reason",isError=true)

    }

    suspend fun onOfficeSelected(index: Int) {
        _uiState.update { state ->
            val facultyListState = state.officeState.copy(selected = index)
            state.copy(officeState = facultyListState)
        }
        val officeId = _uiState.value.officeState.offices[index].id
        loadSubOffices(officeId)

    }

    fun getSubOfficeId(index: Int): String? {
        val subOfficeList = _uiState.value.subOfficeState?.subOffices
        return subOfficeList?.get(index)?.id
    }

    private suspend fun loadSubOffices(officeId: String) {

        startLoading()
        val result = subOfficeListRepository.getSubOffices(officeId)
        if (result.isSuccess) {
            val subOffices = result.getOrDefault(emptyList()).map {
                SubOfficeModel(
                    name = it.name,
                    id = it.subOfficeId,
                    employeeCnt = it.employeeCount.toString()
                )
            }
            onSubOfficeListChanged(subOffices)
        }
        stopLoading()

    }

    private fun onSubOfficeListChanged(subOffices: List<SubOfficeModel>) {
        _uiState.update { state ->
            val subOfficeState = SubOfficeListState(subOffices = subOffices)
            state.copy(subOfficeState = subOfficeState, showSubOfficeList = true)
        }

    }

    fun clearSnackBarMessages() {
        updateSnackBarMessage(null)
    }

    private fun updateSnackBarMessage(
        message: String?,
        details: String? = null,
        isError: Boolean = false
    ) {
        if (message != null) {
            _uiState.update {
                it.copy(
                    snackBarData = CustomSnackBarData(
                        message = message, details = details, isError = isError
                    )
                )
            }

        } else {
            _uiState.update {
                it.copy(
                    snackBarData = null
                )
            }
        }


    }

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }

    }

    private fun stopLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

}

