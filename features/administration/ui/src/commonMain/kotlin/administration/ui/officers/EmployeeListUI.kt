package administration.ui.officers

import administration.controller_presenter.model.AdminOfficerModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.list.AdaptiveList


/*
It this function must be called with a list.
the user have to choice where to place this composable,
will it place in a separate screen or it it place in a  pane dependent on the
client code,but this is responsive enough so it can be used with either separate screen or part of
another screen such as pane
 */



@Composable
internal fun ListOfAdminOfficer(
    modifier: Modifier = Modifier,
    officers: List<AdminOfficerModel>,
    onEvent:(AdminEmployeeListEvent)->Unit,
) {
    if (officers.isNotEmpty()){
        Surface(
            shadowElevation = 8.dp
        ) {
            AdaptiveList(
                modifier = modifier,
                items = officers
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

