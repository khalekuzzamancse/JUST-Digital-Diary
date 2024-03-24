package administration.ui.officers.components

import administration.ui.officers.components.event.AdminEmployeeListEvent
import administration.ui.officers.components.state.AdminOfficerListState
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList


/*
It this function must be called with a list.
the user have to choice where to place this composable,
will it place in a separate screen or it it place in a  pane dependent on the
client code,but this is responsive enough so it can be used with either separate screen or part of
another screen such as pane
 */



@Composable
 fun ListOfAdminOfficer(
    modifier: Modifier = Modifier,
    state: AdminOfficerListState,
    onEvent:(AdminEmployeeListEvent)->Unit,
) {
    if (state.adminOfficers.isNotEmpty()){
        Surface(
            shadowElevation = 8.dp
        ) {
            AdaptiveList(
                modifier = modifier,
                items = state.adminOfficers
            ) { employee ->
                AdminOfficerCard(
                    modifier = Modifier.padding(8.dp),
                    adminOfficer = employee,
                    onCallRequest = {
                        onEvent(AdminEmployeeListEvent.CallRequest(employee.phone))
                    },
                    onMessageRequest = {
                        onEvent(AdminEmployeeListEvent.MessageRequest(employee.phone))
                    },
                    onEmailRequest = {
                        onEvent(AdminEmployeeListEvent.EmailRequest(employee.email))
                    }
                )
            }

        }
    }
}

