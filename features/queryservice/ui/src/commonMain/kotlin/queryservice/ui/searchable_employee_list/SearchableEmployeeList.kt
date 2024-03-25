package queryservice.ui.searchable_employee_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import common.ui.search_bar.decorator.SearchSection

import queryservice.di.EmployeesComponentProvider
import queryservice.ui.event.SearchFunctionalityEvent


@Composable
fun SearchableEmployeeList(
    barLeadingIcon: @Composable () -> Unit = {},
    onEvent: (SearchFunctionalityEvent) -> Unit,
) {

    val viewModel =
        remember { EmployeeListViewModel(EmployeesComponentProvider.getEmployeeRepository()) }
    LaunchedEffect(Unit) {
        viewModel.loadTeacherList()
    }

        SearchableEmployeeList(
            onExitRequest = {
                            onEvent(SearchFunctionalityEvent.ExitRequest)
            },
            employeeList = viewModel.uiState.collectAsState().value.employee,
            onEmailRequest = {
                onEvent(SearchFunctionalityEvent.EmailRequest(it))
            },
            onMessageRequest = {
                onEvent(SearchFunctionalityEvent.MessageRequest(it))
            },
            onCallRequest = {

                onEvent(SearchFunctionalityEvent.CallRequest(it))
            },
            barLeadingIcon = barLeadingIcon
        )


}

@Composable
private fun SearchableEmployeeList(
    barLeadingIcon: @Composable () -> Unit = {},
    employeeList: List<Employee>,
    onExitRequest:()->Unit,
    onCallRequest: (String) -> Unit,
    onEmailRequest: (String) -> Unit,
    onMessageRequest: (String) -> Unit,
) {


        SearchSection(
            onExitRequest = onExitRequest,
            barLeadingIcon = barLeadingIcon,
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



