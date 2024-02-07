package com.just.cse.digital_diary.features.admin_office.components.destination

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.state.SubOffice
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.state.SubOfficeListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.model.AdminOfficeListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.repoisitory.AdminOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.model.AdminSubOfficeListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.repoisitory.AdminSubOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list.state.Offices
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
        when (val result = repository.getAdminOffices()) {
            is AdminOfficeListResponseModel.Success -> {
                onFacultyListFetchedSuccessfully(result.data.map {
                    Offices(
                        name = it.name,
                        id = it.id,
                        numberOfSubOffices = "${it.subOfficeCnt}"
                    )
                })
            }

            is AdminOfficeListResponseModel.Failure -> {
                onFacultyListFetchedFailed(result.reason)
            }


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
        when (val result = subOfficeListRepository.getSubOffices(officeId)) {
            is AdminSubOfficeListResponseModel.Success->{
                onSubOfficeListChanged(result.data.map {
                    SubOffice(
                        name = it.name,
                        id=it.id,
                        employeeCnt = it.employeeCount.toString()
                    )
                })

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

