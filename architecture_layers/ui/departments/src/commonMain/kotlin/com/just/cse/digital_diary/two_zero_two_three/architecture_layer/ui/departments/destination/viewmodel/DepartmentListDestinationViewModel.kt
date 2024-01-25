package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.event.DepartmentListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.state.Department
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.event.DepartmentDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.state.DepartmentDestinationState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.model.DepartmentListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory.DepartmentListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DepartmentListDestinationViewModel(
    private val facultyId: String,
    private val repository: DepartmentListRepository
) {

    private val _state = MutableStateFlow(DepartmentDestinationState())
    val state = _state.asStateFlow()
    private val _selectedDepartmentId= MutableStateFlow<String?>(null)
    val selectedFacultyId=_selectedDepartmentId.asStateFlow()
    var onExitRequest:()->Unit={}

    fun onEvent(event: DepartmentDestinationEvent) {
        when (event) {
            is DepartmentDestinationEvent.ExitRequest -> {
                clearSelectedDepartment()
                onExitRequest()
            }
            is DepartmentListEvent.DepartmentSelected -> onDepartmentSelected(event.index)
        }

    }
    private fun clearSelectedDepartment(){
        onDepartmentSelected(-1)
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            startLoading()
            when (val result = repository.getDepartment(facultyId)) {
                is DepartmentListResponseModel.Success -> {
                    onDepartmentListFetchedSuccessfully(result.data.map {
                        Department(
                            name = it.name,
                            id = it.id,
                            shortName = it.shortName,
                        )
                    })

                }

                is DepartmentListResponseModel.Failure -> {
                    onDepartmentListFetchedFailed(result.reason)
                }


            }
            stopLoading()


        }
    }

    private fun onDepartmentListFetchedSuccessfully(departments: List<Department>) {
        _state.update { state ->
            val departmentListState = state.departmentListState.copy(departments = departments)
            state.copy(departmentListState = departmentListState)
        }

    }

    private fun onDepartmentListFetchedFailed(reason: String?) {
        updateSnackBarMessage(reason)

    }


    private fun onDepartmentSelected(index: Int) {
        _state.update { state ->
            val departmentListState = state.departmentListState.copy(selected = index)
            state.copy(departmentListState = departmentListState)
        }
        _selectedDepartmentId.update { _state.value.departmentListState.departments[index].id }

    }

    private fun clearMessages() {
        updateSnackBarMessage(null)
    }

    private fun updateSnackBarMessage(message: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            _state.update { it.copy(snackBarMessage = message) }
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

