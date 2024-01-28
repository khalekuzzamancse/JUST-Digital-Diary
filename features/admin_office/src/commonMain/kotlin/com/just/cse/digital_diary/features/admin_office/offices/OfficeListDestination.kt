package com.just.cse.digital_diary.features.admin_office.offices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.admin_office.sub_offices.AdminSubOffices
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.destination.AdminOfficesDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.viewmodel.AdminOfficesDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.repoisitory.AdminOfficeListRepositoryImpl

@Composable
fun AdminOffices(
    onEmployeeListRequest:(String)->Unit,
    onExitRequest: () -> Unit,
) {
    val viewModel = remember {
        AdminOfficesDestinationViewModel(
            repository = AdminOfficeListRepositoryImpl(),
        )
    }
    Box(Modifier.fillMaxSize()) {
        AdminOfficesDestination(
            viewModel = viewModel,
            onExitRequest = onExitRequest
        )
        val officeId = viewModel.selectedSubOfficeId.collectAsState().value
        if (officeId != null) {
            Box(Modifier.matchParentSize().background(MaterialTheme.colorScheme.primaryContainer)) {
                AdminSubOffices(
                    officeId = officeId,
                    onExitRequest = {
                    viewModel.closeSubOffices()
                    },
                    onEmployeeListRequest = onEmployeeListRequest
                )
            }
        }
    }


}