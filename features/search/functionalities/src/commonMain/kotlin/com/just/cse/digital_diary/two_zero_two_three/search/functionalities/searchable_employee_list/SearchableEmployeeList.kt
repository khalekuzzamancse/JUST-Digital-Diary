package com.just.cse.digital_diary.two_zero_two_three.search.functionalities.searchable_employee_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.search.search_bar.decorator.SearchSection
import com.just.cse.digital_diary.two_zero_two_three.search.functionalities.employee_list.Employee
import com.just.cse.digitaldiary.twozerotwothree.core.di.employees.EmployeesComponentProvider


@Composable
fun SearchableEmployeeList() {
    val viewModel= remember { EmployeeListViewModel(EmployeesComponentProvider.getEmployeeRepository()) }
    LaunchedEffect(Unit){
        viewModel.loadTeacherList()
    }
    SearchableEmployeeList(
        employeeList = viewModel.uiState.collectAsState().value.employee,
        onEmailRequest = {},
        onMessageRequest = {},
        onCallRequest = {},
        onSearchExitRequest = {}
    )
}

@Composable
private fun SearchableEmployeeList(
    employeeList: List<Employee>,
    onSearchExitRequest: () -> Unit,
    onCallRequest: (String) -> Unit,
    onEmailRequest: (String) -> Unit,
    onMessageRequest: (String) -> Unit,
) {

    SearchSection(
        onSearchExitRequest = onSearchExitRequest,
        filterPredicate = { employee, queryText ->
            val filter = (employee.name.contains(queryText, ignoreCase = true)
                    || employee.additionalEmail.contains(queryText, ignoreCase = true)
                    || employee.designations.contains(queryText, ignoreCase = true)
                    || employee.achievements.contains(queryText, ignoreCase = true)
                    || employee.phone.contains(queryText, ignoreCase = true)
                    || employee.roomNo.contains(queryText, ignoreCase = true)
                    )
            filter
        },
        items = employeeList,
        searchedItemDecorator = { employee, queryText ->
            println("EmployeeLIst:$employeeList")
            SearchedEmployeeCard(
                modifier = Modifier,
                highLightedText = queryText,
                employee = employee,
                onCallRequest = {
                    onCallRequest(employee.phone)
                },
                onMessageRequest = {
                    onMessageRequest(employee.phone)
                },
                onEmailRequest = {
                    onEmailRequest(employee.email)
                }
            )
        },

        )

}



