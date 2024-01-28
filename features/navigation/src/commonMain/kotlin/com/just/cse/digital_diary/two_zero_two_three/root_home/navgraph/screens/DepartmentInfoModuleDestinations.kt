package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.faculty.destination.teacher_list.TeacherList
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent

object DepartmentInfoModuleDestinations {
    val DEPARTMENT_INFO = Destination.createDestination("DEPARTMENT_INFO")

    @Composable
    fun DepartmentInfo(
        departmentId: String,
        appEvent: AppEvent,
        onExitRequest: () -> Unit,
    ) {
        Scaffold(modifier = Modifier,
            topBar = {
                SimpleTopBar(
                    title = "Teacher List",
                    navigationIcon = Icons.Default.Menu,
                    onNavigationIconClick = onExitRequest
                )
            }
        )
        {
            Box(Modifier.padding(it)){
                TeacherList()
            }
        }


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
