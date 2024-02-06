package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.ListOfAdminOfficer
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.viewmodel.AdminOfficerListViewModel

@Composable
fun AdminOfficerListDestination(
    subOfficeId: String,
    modifier: Modifier = Modifier,
    viewModel: AdminOfficerListViewModel
) {
    ListOfAdminOfficer(
        modifier = modifier,
        state = viewModel.uiState.collectAsState().value.adminOfficerListState,
        onEvent = viewModel::onEvent,
    )
    LaunchedEffect(Unit){
        viewModel.setDepartmentId(subOfficeId)
    }

}