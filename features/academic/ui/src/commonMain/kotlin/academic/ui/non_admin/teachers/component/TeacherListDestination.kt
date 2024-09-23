package academic.ui.non_admin.teachers.component

import academic.ui.model.TeacherModel
import academic.ui.non_admin.teachers.TeacherListEvent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.list.AdaptiveList


@Composable
fun TeacherList(
    modifier: Modifier = Modifier,
    teachers: List<TeacherModel>,
    onEvent: (TeacherListEvent) -> Unit,
) {
    AdaptiveList(
        modifier = modifier,
        items = teachers
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