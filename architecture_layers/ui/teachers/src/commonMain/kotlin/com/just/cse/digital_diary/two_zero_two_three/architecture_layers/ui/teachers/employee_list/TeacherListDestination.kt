package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list.EmployeeCard
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list.state.TeacherListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.event.TeacherListEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList

@Composable
fun TeacherList(
    modifier: Modifier = Modifier,
    state: TeacherListState,
    onEvent: (TeacherListEvent) -> Unit,
) {
    AdaptiveList(
        modifier = modifier,
        items = state.teachers
    ) { employee ->
        EmployeeCard(
            modifier = Modifier.padding(8.dp),
            teacher = employee,
            onCallRequest = {
                onEvent(TeacherListEvent.CallRequest(employee.phone))
            },
            onMessageRequest = {
                onEvent(TeacherListEvent.MessageRequest(employee.phone))
            },
            onEmailRequest = {
                onEvent(TeacherListEvent.EmailRequest(employee.email))
            }
        )
    }


}