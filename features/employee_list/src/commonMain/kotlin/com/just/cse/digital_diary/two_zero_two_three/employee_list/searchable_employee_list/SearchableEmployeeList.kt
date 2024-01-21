package com.just.cse.digital_diary.two_zero_two_three.employee_list.searchable_employee_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.search.search_bar.decorator.SearchSection
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Employee


@Composable
fun SearchableEmployeeList(
    employeeList: List<Employee>,
    onSearchExitRequest: () -> Unit,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {
    SearchSection(
        onSearchExitRequest = onSearchExitRequest,
        filterPredicate = { employee, queryText ->
            val filter = (employee.name.contains(queryText, ignoreCase = true)
                    || employee.deptName.contains(queryText, ignoreCase = true)
                    || employee.deptSortName.contains(queryText, ignoreCase = true)
                    || employee.additionalEmail.contains(queryText, ignoreCase = true)
                    || employee.designations.contains(queryText, ignoreCase = true)
                    || employee.achievements.contains(queryText, ignoreCase = true)
                    || employee.phone.contains(queryText, ignoreCase = true)
                    || employee.roomName.contains(queryText, ignoreCase = true)
                    )
            filter
        },
        items = employeeList,
        searchedItemDecorator = { employee, queryText ->
            SearchedEmployeeCard(
                modifier = Modifier,
                highLightedText = queryText,
                employee = employee,
                onCallRequest=onCallRequest,
                onMessageRequest = onMessageRequest,
                onEmailRequest = onEmailRequest
            )
        },

        )

}



