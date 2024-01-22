package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen

import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.data.DepartmentInfoRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.employee_list_repoisitory.model.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ViewModel {
    private val _employeeList = MutableStateFlow<List<Employee>?>(null)
    val employees = _employeeList.asStateFlow()
    private val _selectedDestination = MutableStateFlow(-1)
    val selectedDestination = _selectedDestination.asStateFlow()
    private val scope = CoroutineScope(Dispatchers.Default)
    fun updateEmployee(type: Int) {
        _selectedDestination.update { type }
        when (type) {
            0 -> {
                scope.launch {
                    _employeeList.update { DepartmentInfoRepository.getTeacherList("01") }
                }

            }

            1 -> {
                scope.launch {
                    _employeeList.update {
                        DepartmentInfoRepository.getStaffList("01").map {
                            it.copy(name = "Staff:${it.name}")
                        }
                    }

                }

            }
        }
    }

    fun clearEmployeeList() {
        _employeeList.update { null }
    }

}