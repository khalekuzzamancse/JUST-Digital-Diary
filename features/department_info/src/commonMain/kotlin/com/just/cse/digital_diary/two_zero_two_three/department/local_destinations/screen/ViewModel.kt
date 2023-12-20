package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen

import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_employee_list_repoisitory.data.EmployeeRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.data.DepartmentInfoRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_employee_list_repoisitory.model.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ViewModel(
    private val departmentId:String
) {
    private val _employeeList = MutableStateFlow<List<Employee>?>(null)
    val employees = _employeeList.asStateFlow()
    private val _selectedDestination = MutableStateFlow(0)
    val selectedDestination = _selectedDestination.asStateFlow()
    private val scope = CoroutineScope(Dispatchers.Default)
    fun updateEmployee(type: Int) {
        _selectedDestination.update { type }
        when (type) {
            1 -> {
                scope.launch {
                    _employeeList.update { EmployeeRepository.getEmployees(departmentId) }
                }

            }

            2 -> {
                scope.launch {
                    _employeeList.update { EmployeeRepository.getStafff(departmentId) }

                }

            }
        }
    }

    fun clearEmployeeList() {
        _employeeList.update { null }
    }

}