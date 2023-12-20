package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.just.cse.digital_diary.two_zero_two_three.employee_list.searchable_employee_list.SearchableEmployeeList
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.data.DepartmentInfoRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_employee_list_repoisitory.model.Employee

var cnt=1
@Composable
internal fun SearchDestination(
    onExitRequest: () -> Unit,
    onCallRequest: (String) -> Unit,
    onEmailRequest: (String) -> Unit,
    onMessageRequest: (String) -> Unit,
) {
    var employees by remember { mutableStateOf<List<Employee>>(emptyList()) }
    LaunchedEffect(Unit){
        employees= DepartmentInfoRepository.getTeacherList("01")
    }
    SearchableEmployeeList(
        onSearchExitRequest = onExitRequest,
        employeeList = employees,
        onCallRequest =onCallRequest,
        onMessageRequest =onMessageRequest,
        onEmailRequest =onEmailRequest
    )


}