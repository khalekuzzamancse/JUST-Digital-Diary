package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.event.FacultyListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.state.Faculty
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.event.FacultyDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.state.FacultyDestinationState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.model.FacultyListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory.FacultyListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FacultyDestinationViewModel(
    private val repository: FacultyListRepository
) {

    private val _state = MutableStateFlow(FacultyDestinationState())
    val state = _state.asStateFlow()
    private val _selectedFacultyId= MutableStateFlow<String?>(null)
    val selectedFacultyId=_selectedFacultyId.asStateFlow()

    fun onEvent(event: FacultyDestinationEvent) {
        when (event) {
            is FacultyDestinationEvent.ExitRequest -> {}
            is FacultyListEvent.FacultySelected -> onFacultySelected(event.index)
        }

    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            startLoading()
            when (val result = repository.getFaculties()) {
                is FacultyListResponseModel.Success -> {
                    onFacultyListFetchedSuccessfully(result.data.map {
                        Faculty(
                            name = it.name,
                            id = it.facultyId,
                            numberOfDepartment = "${it.departmentsCount}"
                        )
                    })

                }

                is FacultyListResponseModel.Failure -> {
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
        CoroutineScope(Dispatchers.IO).launch {
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

