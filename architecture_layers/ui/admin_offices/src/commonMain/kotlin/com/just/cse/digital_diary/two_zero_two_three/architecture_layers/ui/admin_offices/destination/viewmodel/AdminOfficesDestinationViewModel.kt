package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.model.AdminOfficeListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.faculty_list.state.Faculty
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.repoisitory.AdminOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.event.AdminOfficesDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.state.AdminOfficesDestinationState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.faculty_list.event.AdminOfficesListEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdminOfficesDestinationViewModel(
    private val repository: AdminOfficeListRepository
) {

    private val _state = MutableStateFlow(AdminOfficesDestinationState())
    val state = _state.asStateFlow()
    private val _selectedFacultyId= MutableStateFlow<String?>(null)
    val selectedFacultyId=_selectedFacultyId.asStateFlow()

    fun onEvent(event: AdminOfficesDestinationEvent) {
        when (event) {
            is AdminOfficesDestinationEvent.ExitRequest -> {}
            is AdminOfficesListEvent.AdminOfficesSelected -> onFacultySelected(event.index)
        }

    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            startLoading()
            when (val result = repository.getAdminOffices()) {
                is AdminOfficeListResponseModel.Success -> {
                    onFacultyListFetchedSuccessfully(result.data.map {
                        Faculty(
                            name = it.name,
                            id = it.id,
                            numberOfDepartment = "${it.subOfficeCnt}"
                        )
                    })

                }

                is AdminOfficeListResponseModel.Failure -> {
                    onFacultyListFetchedFailed(result.reason)
                }


            }
            stopLoading()


        }
    }

    private fun onFacultyListFetchedSuccessfully(faculties: List<Faculty>) {
        _state.update { state ->
            val facultyListState = state.facultyListState.copy(faculties = faculties)
            state.copy(facultyListState = facultyListState)
        }

    }

    private fun onFacultyListFetchedFailed(reason: String?) {
        updateSnackBarMessage(reason)

    }


    private fun onFacultySelected(index: Int) {
        _state.update { state ->
            val facultyListState = state.facultyListState.copy(selected = index)
            state.copy(facultyListState = facultyListState)
        }
        _selectedFacultyId.update { _state.value.facultyListState.faculties[index].id }

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

