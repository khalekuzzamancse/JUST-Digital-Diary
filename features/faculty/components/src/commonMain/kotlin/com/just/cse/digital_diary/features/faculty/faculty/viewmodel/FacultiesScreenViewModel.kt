package com.just.cse.digital_diary.features.faculty.faculty.viewmodel

import com.just.cse.digital_diary.features.faculty.faculty.state.FacultiesScreenState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.state.Faculty
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.state.FacultyListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.state.Department
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.state.DepartmentListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.model.DepartmentListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory.DepartmentListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.model.FacultyListResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory.FacultyListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FacultiesScreenViewModel(
    private val facultyListRepository: FacultyListRepository,
    private val departmentListRepository: DepartmentListRepository
) {

    private val _departmentListState = MutableStateFlow<DepartmentListState?>(null)
    val departmentListState = _departmentListState.asStateFlow()
    private val _facultyListState = MutableStateFlow(FacultyListState(faculties = emptyList()))
    val facultyListState = _facultyListState.asStateFlow()

    fun onFacultySelected(index: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val facultyId = facultyListState.value.faculties[index].id
            loadDepartmentList(facultyId)
            _uiState.update { it.copy(openDepartmentListDestination = true) }
        }
    }


    suspend fun loadFacultyList() {
        startLoading()
        when (val result = facultyListRepository.getFaculties()) {
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
                onFailure(result.reason)
            }
        }
        stopLoading()

    }

    private suspend fun onFailure(reason: String?) {
        if (reason != null)
            showErrorMessage(reason)
    }

    private suspend fun showErrorMessage(errorMessage: String) {
        _uiState.update {
            it.copy(message = errorMessage)
        }
        delay(1000)
        _uiState.update {
            it.copy(message = null)
        }
    }

    private fun onFacultyListFetchedSuccessfully(faculties: List<Faculty>) {
        _facultyListState.update { state ->
            state.copy(faculties = faculties)
        }

    }

    private fun startLoading() {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    private fun stopLoading() {
        _uiState.update {
            it.copy(isLoading = false)
        }
    }


    private suspend fun loadDepartmentList(facultyId: String) {
        startLoading()
        when (val result = departmentListRepository.getDepartment(facultyId)) {
            is DepartmentListResponseModel.Success -> {
                val departments = result.data.map {
                    Department(
                        name = it.name,
                        id = it.id,
                        shortName = it.shortName,
                    )
                }
                _departmentListState.update { state ->
                    state?.copy(departments = departments) ?: DepartmentListState(departments = departments)

                }

            }
            is DepartmentListResponseModel.Failure -> {
                onFailure(result.reason)
            }
        }
        stopLoading()
    }

    fun clearFacultySelection() {
        _facultyListState.update { it.copy(selected = -1) }
        _departmentListState.update { null }
        _uiState.update { it.copy(openDepartmentListDestination = false) }
    }

    private val _uiState = MutableStateFlow(FacultiesScreenState())
    val uiState = _uiState.asStateFlow()


}
