package faculty.ui.faculty.route.viewmodel

import faculty.ui.faculty.route.state.FacultiesScreenState
import faculty.domain.department.repoisitory.DepartmentListRepository
import faculty.domain.faculties.repoisitory.FacultyListRepository
import faculty.ui.department.components.component.Department
import faculty.ui.department.components.component.DepartmentListState
import faculty.ui.faculty.components.faculty_list.Faculty
import faculty.ui.faculty.components.faculty_list.FacultyListState
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

    private val _uiState = MutableStateFlow(FacultiesScreenState())
    val uiState = _uiState.asStateFlow()

    fun onFacultySelected(index: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            _facultyListState.update { it.copy(selected = index) }
            val facultyId = facultyListState.value.faculties[index].id
            loadDepartmentList(facultyId)
            _uiState.update { it.copy(openDepartmentListDestination = true) }
        }
    }


    suspend fun loadFacultyList() {
        startLoading()
        val result = facultyListRepository.getFaculties()
        if (result.isSuccess) {
            val facultyList = result.getOrDefault(emptyList())
            onFacultyListFetchedSuccessfully(facultyList.map {
                Faculty(
                    name = it.name,
                    id = it.facultyId,
                    numberOfDepartment = "${it.departmentsCount}"
                )
            })
        } else {
            val reason = result.exceptionOrNull()?.message
            onFailure(reason)
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
        val result = departmentListRepository.getDepartment(facultyId)
        if (result.isSuccess) {
            val departments = result.getOrDefault(emptyList()).map {
                Department(
                    name = it.name,
                    id = it.id,
                    shortName = it.shortName,
                )
            }
            _departmentListState.update { state ->
                state?.copy(departments = departments)
                    ?: DepartmentListState(departments = departments)

            }

        } else {
            val reason = result.exceptionOrNull()?.message
            onFailure(reason)
        }
        stopLoading()
    }

    fun getDepartmentId(index: Int): String? {
        _departmentListState.value?.let { state ->
            return state.departments[index].id
        }
        return null
    }

    fun clearFacultySelection() {
        _facultyListState.update { it.copy(selected = null) }
        _departmentListState.update { null }
        _uiState.update { it.copy(openDepartmentListDestination = false) }
    }


}
