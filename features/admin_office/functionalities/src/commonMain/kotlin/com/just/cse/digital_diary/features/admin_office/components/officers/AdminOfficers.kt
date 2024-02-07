package com.just.cse.digital_diary.features.admin_office.components.officers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.admin_office.components.officers.viewmodel.AdminOfficerListViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.ListOfAdminOfficer
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.AdminOfficerListRepository
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator

@Composable
fun AdminOfficers(
    subOfficeId: String,
    repository: AdminOfficerListRepository,
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
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading
    ) {
        ListOfAdminOfficer(
            modifier = Modifier,
            state = viewModel.uiState.collectAsState().value.adminOfficerListState,
            onEvent = {}
        )

    }


}