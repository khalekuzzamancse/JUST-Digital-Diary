package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.event.SubOfficeListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.state.SubOffice
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.event.SubOfficeDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.state.SubOfficeListDestinationState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.model.AdminSubOfficeListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.repoisitory.AdminSubOfficeListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SubOfficeListDestinationViewModel(
    private val repository: AdminSubOfficeListRepository
) {

    private val subOfficeId= MutableStateFlow<String?>(null)
    private val _state = MutableStateFlow(SubOfficeListDestinationState())
    val state = _state.asStateFlow()
    private val _selectedSubOfficeId = MutableStateFlow<String?>(null)
    val selectedSubOfficeId = _selectedSubOfficeId.asStateFlow()
    var onExitRequest: () -> Unit = {}
    fun changeSubOfficeId(id:String){
        subOfficeId.update { id }
    }

    fun onEvent(event: SubOfficeDestinationEvent) {
        when (event) {
            is SubOfficeDestinationEvent.ExitRequest -> {
                clearSelectedDepartment()
                onExitRequest()
            }

            is SubOfficeListEvent.SubOfficeSelected -> onDepartmentSelected(event.index)
            is SubOfficeListEvent.DismissRequest -> {
                clearSelectedDepartment()
                onExitRequest()
            }
        }

    }

    private fun clearSelectedDepartment() {
        onDepartmentSelected(-1)
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            subOfficeId.collect{ facultyId->
                if(facultyId!=null){
                    startLoading()
                    when (val result = repository.getSubOffices()) {
                        is AdminSubOfficeListResponseModel.Success -> {
                            onDepartmentListFetchedSuccessfully(result.data.map {
                                SubOffice(
                                    name = it.name,
                                    id = it.id,
                                    employeeCnt = "${it.employeeCount}"
                                )
                            })

                        }

                        is AdminSubOfficeListResponseModel.Failure -> {
                            onDepartmentListFetchedFailed(result.reason)
                        }


                    }
                    stopLoading()
                }

            }



        }
    }

    private fun onDepartmentListFetchedSuccessfully(subOffices: List<SubOffice>) {
        CoroutineScope(Dispatchers.Default).launch {
            _state.update { state ->
                val departmentListState = state.subOfficeListState.copy(subOffices = subOffices)
                state.copy(subOfficeListState = departmentListState)
            }

        }


    }

    private fun onDepartmentListFetchedFailed(reason: String?) {
        updateSnackBarMessage(reason)

    }


    private fun onDepartmentSelected(index: Int) {
        _state.update { state ->
            val departmentListState = state.subOfficeListState.copy(selected = index)
            state.copy(subOfficeListState = departmentListState)
        }
        if (index>=0){
            _selectedSubOfficeId.update { _state.value.subOfficeListState.subOffices[index].id }
        }

    }

    private fun clearMessages() {
        updateSnackBarMessage(null)
    }

    private fun updateSnackBarMessage(message: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            _state.update { it.copy(errorMessage = message) }
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

