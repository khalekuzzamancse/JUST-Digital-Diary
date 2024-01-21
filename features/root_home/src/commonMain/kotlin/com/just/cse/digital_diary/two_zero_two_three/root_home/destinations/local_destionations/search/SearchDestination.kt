package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.search

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.employee_list.searchable_employee_list.SearchableEmployeeList
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository

var cnt=1
@Composable
internal fun SearchDestination(
    onExitRequest: () -> Unit,
    onCallRequest: (String) -> Unit,
    onEmailRequest: (String) -> Unit,
    onMessageRequest: (String) -> Unit,
) {
    println("Recomposition:SearchDestination:$cnt")
    cnt++
    SearchableEmployeeList(
        onSearchExitRequest = onExitRequest,
        employeeList = DepartmentInfoRepository.getTeacherList("01"),
        onCallRequest =onCallRequest,
        onMessageRequest =onMessageRequest,
        onEmailRequest =onEmailRequest
    )


}