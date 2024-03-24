package administration.ui.offices.route

import admin_office.domain.offices.repoisitory.AdminOfficeListRepository
import admin_office.domain.sub_offices.repoisitory.AdminSubOfficeListRepository
import administration.ui.offices.components.components.office_list.state.Offices
import administration.ui.sub_offices.component.component.sub_office_list.state.SubOffice
import administration.ui.sub_offices.component.component.sub_office_list.state.SubOfficeListState

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

    private val _state = MutableStateFlow(AdminOfficesDestinationState())
    val state = _state.asStateFlow()


    suspend fun loadOffices() {
        startLoading()
        val result = repository.getAdminOffices()
        if (result.isSuccess){
            onFacultyListFetchedSuccessfully(result.getOrDefault(emptyList()).map {
                Offices(
                    name = it.name,
                    id = it.id,
                    numberOfSubOffices = "${it.subOfficeCnt}"
                )
            })
        }
        else{
            onFacultyListFetchedFailed(result.exceptionOrNull()?.message)

        }

        stopLoading()

    }

    private fun onFacultyListFetchedSuccessfully(faculties: List<Offices>) {
        _state.update { state ->
            val facultyListState = state.officeState.copy(offices = faculties)
            state.copy(officeState = facultyListState)
        }

    }
    fun dismissSubOfficesDestination(){
        _state.update { state ->
            state.copy(subOfficeState = null, showSubOfficeList = false)
        }
    }

    private fun onFacultyListFetchedFailed(reason: String?) {

        updateSnackBarMessage(reason)

    }
    suspend fun onOfficeSelected(index: Int) {
        _state.update { state ->
            val facultyListState = state.officeState.copy(selected = index)
            state.copy(officeState = facultyListState)
        }
        val officeId=_state.value.officeState.offices[index].id
        loadSubOffices(officeId)

    }
    fun getSubOfficeId(index: Int):String?{
        val subOfficeList= _state.value.subOfficeState?.subOffices
        return subOfficeList?.get(index)?.id
    }

    private suspend fun loadSubOffices(officeId: String) {
        startLoading()
       val result = subOfficeListRepository.getSubOffices(officeId)
        if (result.isSuccess){
            result.getOrDefault(emptyList()).map {
                SubOffice(
                    name = it.name,
                    id=it.id,
                    employeeCnt = it.employeeCount.toString()
                )
            }
        }
        stopLoading()

    }
    private fun onSubOfficeListChanged(subOffices:List<SubOffice>){
        _state.update { state ->
            val subOfficeState = SubOfficeListState(subOffices=subOffices)
            state.copy(subOfficeState = subOfficeState, showSubOfficeList = true)
        }

    }

    private fun clearMessages() {
        updateSnackBarMessage(null)
    }

    private fun updateSnackBarMessage(message: String?) {
        CoroutineScope(Dispatchers.Default).launch {
            _state.update { it.copy(message = message) }
            delay(1500)
            clearMessages()
        }

    }

    private fun startLoading() {
        _state.update { it.copy(isLoading = true) }

    }

    private fun stopLoading() {
        _state.update { it.copy(isLoading = false) }
    }

}

