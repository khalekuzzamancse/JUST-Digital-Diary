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
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {
    Surface(
        shadowElevation = 8.dp
    ) {
        AdaptiveEmployeeList(
            modifier = modifier.wrapContentWidth(),
            employees = employees,
            onCallRequest=onCallRequest,
            onMessageRequest = onMessageRequest,
            onEmailRequest = onEmailRequest
        )
    }


}

@Composable
private fun AdaptiveEmployeeList(
    modifier: Modifier = Modifier,
    employees: List<Employee>,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {
    AdaptiveList(
        modifier = modifier,
        items = employees
    ) { teacher ->
        EmployeeCard(
            modifier = Modifier.padding(8.dp),
            employee = teacher,
            onCallRequest=onCallRequest,
            onMessageRequest = onMessageRequest,
            onEmailRequest = onEmailRequest
        )
    }
}
