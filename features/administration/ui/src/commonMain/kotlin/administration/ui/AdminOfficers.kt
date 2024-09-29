package administration.ui

import administration.factory.UiFactory
import administration.ui.officers.AdminEmployeeListEvent
import administration.ui.officers.ListOfAdminOfficer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import common.ui.progressbar.ProgressBarNSnackBarDecorator


@Composable
internal fun AdminOfficers(
    modifier: Modifier=Modifier,
    token: String,
    subOfficeId: String,
    onEvent: (AdminEmployeeListEvent)->Unit
) {
    val viewModel = remember {
        EmployeeListViewModel(
            controller = UiFactory.createEmployeeController(token = token)
        )
    }
    val controller =viewModel.controller
    val employees=controller.employees.collectAsState().value
    LaunchedEffect(Unit) {
        controller.fetch(subOfficeId = subOfficeId)
    }

    ProgressBarNSnackBarDecorator(
        modifier=Modifier,
        showProgressBar = viewModel.isLoading.collectAsState().value
    ) {
        ListOfAdminOfficer(
            modifier = modifier,
            officers = employees,
            onEvent = onEvent
        )

    }


}
