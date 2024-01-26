package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.event.TeachersDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.event.TeacherListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.state.TeacherListState
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList


/*
It this function must be called with a list.
the user have to choice where to place this composable,
will it place in a separate screen or it it place in a  pane dependent on the
client code,but this is responsive enough so it can be used with either separate screen or part of
another screen such as pane
 */



@Composable
internal fun ListOfTeacher(
    modifier: Modifier = Modifier,
    state: TeacherListState,
    onEvent:(TeachersDestinationEvent)->Unit,
) {
    Surface(
        shadowElevation = 8.dp
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
}

