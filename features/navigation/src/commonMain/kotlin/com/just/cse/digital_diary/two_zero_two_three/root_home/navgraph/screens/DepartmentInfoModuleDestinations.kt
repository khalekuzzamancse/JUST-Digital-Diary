package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.destination.teacher_list.TeacherList
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent

object DepartmentInfoModuleDestinations {
    val DEPARTMENT_INFO=Destination.createDestination("DEPARTMENT_INFO")
    @Composable
    fun DepartmentInfo(
        departmentId: String,
        appEvent: AppEvent,
        onExitRequest: () -> Unit,
    ) {
        TeacherList()

//        DepartmentDestinationList(
//            viewModel = DepartmentListDestinationViewModel(
//                repository =DepartmentListRepositoryImpl()
//            ),
//            onEmployeeListRequest = {
//                println("Employee list request")
//            }
//        )
    }

}
