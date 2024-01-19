package com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.faculties

import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Department
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.FacultyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class ViewModel (
    private val onDepartmentNavigationRequest:(String)->Unit,
){
    private val _faculties = MutableStateFlow(FacultyRepository.faculties)
    val faculties = _faculties.asStateFlow()
    private val _selectedFacultyDepartments = MutableStateFlow<List<Department>>(emptyList())
    val selectedFacultyDepartments = _selectedFacultyDepartments.asStateFlow()
    private val _selectedFaculty = MutableStateFlow(0)
    val selectedFaculty = _selectedFaculty.asStateFlow()
    private val _selectedDepartment = MutableStateFlow(0)
    val selectedDepartment = _selectedDepartment.asStateFlow()


    fun onFacultySelected(index: Int) {
        _selectedFaculty.update { index }
        _selectedFacultyDepartments.update { FacultyRepository.getDepartments(_faculties.value[index].id) }
    }

    fun onDepartmentSelected(index: Int) {
        _selectedDepartment.update { index }
        onDepartmentNavigationRequest(
            _selectedFacultyDepartments.value[index].id
        )

    }


    fun onClearDepartmentSelection() {
        _selectedDepartment.update { 0 }
        _selectedFacultyDepartments.update { emptyList() }
    }
}

