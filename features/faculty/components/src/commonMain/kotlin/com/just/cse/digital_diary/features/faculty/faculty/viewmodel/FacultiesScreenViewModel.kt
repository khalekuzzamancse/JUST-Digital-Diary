package com.just.cse.digital_diary.features.faculty.faculty.viewmodel

import com.just.cse.digital_diary.features.faculty.faculty.state.FacultiesScreenState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.viewmodel.FacultyDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel.DepartmentListDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory.DepartmentListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory.FacultyListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FacultiesScreenViewModel (
    private val facultyListRepository:FacultyListRepository,
    private val departmentListRepository: DepartmentListRepository
){
    val facultyViewModel = FacultyDestinationViewModel(repository = facultyListRepository)

    var departmentViewModel = DepartmentListDestinationViewModel(departmentListRepository)
        private set
    private val _uiState = MutableStateFlow(FacultiesScreenState())
    val uiState = _uiState.asStateFlow()
    val selectedDepartmentId=departmentViewModel.selectedDeptId

    init {
        CoroutineScope(Dispatchers.Default).launch {
            observeSelectedFaculty()
        }
        CoroutineScope(Dispatchers.Default).launch {
            observeDepartmentDestination()
        }
        CoroutineScope(Dispatchers.Default).launch {
            departmentViewModel.onExitRequest = {
                _uiState.update { uiState -> uiState.copy(openDepartmentListDestination = false) }
            }
        }


    }

    private suspend fun observeDepartmentDestination() {
        departmentViewModel.state.collect { deptListDestinationState ->
            _uiState.update { it.copy(
                isLoading = deptListDestinationState.isLoading,
                message = deptListDestinationState.snackBarMessage,

            ) }
        }


    }

    private suspend fun observeSelectedFaculty() {
        facultyViewModel.selectedFacultyId.collect { selectedFacultyId ->
            if (selectedFacultyId != null) {
                departmentViewModel.changeFacultyId(selectedFacultyId)
                _uiState.update { it.copy(openDepartmentListDestination = true) }
            }

        }
    }
}