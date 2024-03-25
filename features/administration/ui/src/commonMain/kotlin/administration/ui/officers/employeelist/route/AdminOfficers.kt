package administration.ui.officers.employeelist.route

import admin_office.domain.officers.repoisitory.AdminOfficerListRepository
import administration.ui.AdminFeatureEvent
import administration.ui.officers.employeelist.components.ListOfAdminOfficer
import administration.ui.officers.employeelist.components.AdminEmployeeListEvent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator

@Composable
internal fun AdminOfficers(
    modifier: Modifier=Modifier,
    subOfficeId: String,
    repository: AdminOfficerListRepository,
    onEvent: (AdminFeatureEvent)->Unit
) {
    val viewModel = remember {
        AdminOfficerListViewModel(
            repository = repository,
            subOfficeId = subOfficeId,
        )
    }
    LaunchedEffect(Unit) {
        viewModel.updateTeacherList()
    }

    ProgressBarNSnackBarDecorator(
        modifier=Modifier,
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading
    ) {
        ListOfAdminOfficer(
            modifier = modifier,
            state = viewModel.uiState.collectAsState().value.adminOfficerListState,
            onEvent = {event->
               convertEvent(event)?.let { onEvent(it) }
            }
        )

    }


}
private fun convertEvent(event: AdminEmployeeListEvent):AdminFeatureEvent?{
    val ev: AdminFeatureEvent? = when (event) {
        is AdminEmployeeListEvent.CallRequest -> AdminFeatureEvent.CallRequest(event.number)
        is AdminEmployeeListEvent.MessageRequest -> AdminFeatureEvent.MessageRequest(
            event.number
        )
        is AdminEmployeeListEvent.EmailRequest -> AdminFeatureEvent.EmailRequest(event.email)
        else -> {
            null
        }
    }
    return ev

}