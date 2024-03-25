package administration.ui.officers.employeelist.route

import admin_office.domain.officers.repoisitory.AdminOfficerListRepository
import administration.ui.officers.employeelist.components.ListOfAdminOfficer
import administration.ui.officers.employeelist.components.AdminEmployeeListEvent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import common.ui.progressbar.ProgressBarNSnackBarDecorator


@Composable
internal fun AdminOfficers(
    modifier: Modifier=Modifier,
    subOfficeId: String,
    repository: AdminOfficerListRepository,
    onEvent: (AdminEmployeeListEvent)->Unit
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
            onEvent = onEvent
        )

    }


}
