package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home.Home
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.staff_list.DepartmentStaffListDestinationContent
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.teacher_list.DepartmentTeacherListDestinationContent
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class ViewModel{
    private val _employeeList= MutableStateFlow<List<Employee>?>(null)
    val employees=_employeeList.asStateFlow()
    fun updateEmployee(type:Int){
        when(type){
            0->{
                _employeeList.update {DepartmentInfoRepository.getTeacherList("01") }
            }
            1->{
                _employeeList.update { DepartmentInfoRepository.getStaffList("01")}
            }
        }
    }
    fun clearEmployeeList(){
        _employeeList.update { null }
    }

}
@Composable
fun Screen(
    departmentId:String,
    onExitRequested:()->Unit
) {
    val viewModel= remember { ViewModel() }
    WindowSizeDecorator(
        onCompact = {
            CompactScreenLayout(
                homeContent = {
                    Home(modifier = Modifier)

                },
                teacherList = {
                    DepartmentTeacherListDestinationContent(
                        teachers = DepartmentInfoRepository.getTeacherList(departmentId)
                    )
                },
                staffList = {
                    DepartmentStaffListDestinationContent(
                        staffs = DepartmentInfoRepository.getTeacherList(departmentId)
                    )
                },
                onExitRequested = onExitRequested
            )

        },
        onNonCompact = {
            NonCompactScreenLayout(
                homeContent ={
                    Home(modifier = Modifier)
                },
                employees = viewModel.employees.collectAsState().value,
                onEmployeeListRequest = viewModel::updateEmployee,
                onEmployeeListDismissRequest = viewModel::clearEmployeeList
            )
        }
    )

}