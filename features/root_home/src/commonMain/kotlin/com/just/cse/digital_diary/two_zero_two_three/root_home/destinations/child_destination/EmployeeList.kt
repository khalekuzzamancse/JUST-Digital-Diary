package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.employee_list.employee_list.EmployeeList
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.data.DepartmentInfoRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.employee_list_repoisitory.model.Employee

@Composable
internal fun AdminOfficeSubOfficeEmployeeDestination(
    subOfficeId: String,
    appEvent: AppEvent,
    onExitRequest: () -> Unit,
) {
    var employees by remember { mutableStateOf<List<Employee>>(emptyList()) }
    LaunchedEffect(Unit){
       employees= DepartmentInfoRepository.getTeacherList("01")
    }
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Employees List",
                onNavigationIconClick = onExitRequest,
                navigationIcon = Icons.Default.Menu
            )
        }
    ) {
        EmployeeList(
            modifier = Modifier.padding(it).fillMaxSize(),
            employees = employees,
            onCallRequest = appEvent.onCallRequest,
            onEmailRequest = appEvent.onEmailRequest,
            onMessageRequest = appEvent.onMessageRequest
        )
    }


}