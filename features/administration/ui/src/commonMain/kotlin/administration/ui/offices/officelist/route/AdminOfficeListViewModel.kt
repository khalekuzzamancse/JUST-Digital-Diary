package administration.ui.offices.officelist.route

import admin_office.domain.offices.repoisitory.AdminOfficeListRepository
import admin_office.domain.sub_offices.repoisitory.AdminSubOfficeListRepository
import administration.ui.offices.officelist.components.Offices
import administration.ui.suboffice.subofficelist.SubOffice
import administration.ui.suboffice.subofficelist.SubOfficeListState
import common.newui.CustomSnackBarData

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminOfficeListViewModel(
    private val repository: AdminOfficeListRepository,
    private val subOfficeListRepository: AdminSubOfficeListRepository
) {

    private val _uiState = MutableStateFlow(AdminOfficesDestinationState())
    val uiState = _uiState.asStateFlow()


    suspend fun loadOffices() {
        startLoading()
        val result = repository.getAdminOffices()
        if (result.isSuccess) {
            onFacultyListFetchedSuccessfully(result.getOrDefault(emptyList()).map {
                Offices(
                    name = it.name,
                    id = it.id,
                    numberOfSubOffices = "${it.subOfficeCnt}"
                )
            })
        } else {
            onFacultyListFetchedFailed(result.exceptionOrNull()?.message)

        }

        stopLoading()

    }

    private fun onFacultyListFetchedSuccessfully(faculties: List<Offices>) {
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

        updateSnackBarMessage(reason)

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
                SubOffice(
                    name = it.name,
                    id = it.id,
                    employeeCnt = it.employeeCount.toString()
                )
            }
            onSubOfficeListChanged(subOffices)
        }
        stopLoading()

    }

    private fun onSubOfficeListChanged(subOffices: List<SubOffice>) {
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

