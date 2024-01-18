package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen

import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class ViewModel{
    private val _employeeList= MutableStateFlow<List<Employee>?>(null)
    val employees=_employeeList.asStateFlow()
    private val _selectedDestination= MutableStateFlow(-1)
    val selectedDestination=_selectedDestination.asStateFlow()
    fun updateEmployee(type:Int){
        _selectedDestination.update { type }
        when(type){
            0->{
                _employeeList.update { DepartmentInfoRepository.getTeacherList("01") }

            }
            1->{
                _employeeList.update { DepartmentInfoRepository.getStaffList("01").map {
                    it.copy(name = "Staff:${it.name}")
                }

                }

            }
        }
    }
    fun clearEmployeeList(){
        _employeeList.update { null }
    }

}