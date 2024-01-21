package com.just.cse.digital_diary.two_zero_two_three.employee_list.employee_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Employee


/*
It this function must be called with a list.
the user have to choice where to place this composable,
will it place in a separate screen or it it place in a  pane dependent on the
client code,but this is responsive enough so it can be used with either separate screen or part of
another screen such as pane
 */



@Composable
fun EmployeeList(
    modifier: Modifier = Modifier,
    employees: List<Employee>,
    onCallRequest: (String) -> Unit,
    onEmailRequest: (String) -> Unit,
    onMessageRequest: (String) -> Unit,
) {
    Surface(
        shadowElevation = 8.dp
    ) {
        AdaptiveList(
            modifier = modifier,
            items = employees
        ) { employee ->
            EmployeeCard(
                modifier = Modifier.padding(8.dp),
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
        }

    }
}

