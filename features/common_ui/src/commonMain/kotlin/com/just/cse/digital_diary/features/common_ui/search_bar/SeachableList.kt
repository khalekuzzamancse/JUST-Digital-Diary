package com.just.cse.digital_diary.features.common_ui.search_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Employee


@Composable
fun SearchableEmployeeList(
    searchMode: Boolean = false,
    onSearchExitRequest:()->Unit,
    employeeList: List<Employee>,
) {

    AnimatedVisibility(
        visible = searchMode
    ) {
        SearchSection(
            onSearchExitRequest =onSearchExitRequest,
            predicate = { employee, queryText ->
                predicate(employee, queryText)
            },
            items = employeeList,
            searchedItemDecorator = { employee, queryText ->
                EmployeeCard(
                    modifier = Modifier,
                    highlightedText = queryText,
                    employee = employee
                )
            },

        )
    }
    AnimatedVisibility(
        visible = !searchMode
    ){
        EmployeeList(
            modifier = Modifier,
            employees = employeeList,
        )
    }
}



fun predicate(employee: Employee, queryText: String): Boolean {
    return (
            employee.name.contains(queryText, ignoreCase = true)
                    || employee.deptName.contains(queryText, ignoreCase = true)
                    || employee.deptSortName.contains(queryText, ignoreCase = true)
                    || employee.email.contains(queryText, ignoreCase = true)
            )
}
