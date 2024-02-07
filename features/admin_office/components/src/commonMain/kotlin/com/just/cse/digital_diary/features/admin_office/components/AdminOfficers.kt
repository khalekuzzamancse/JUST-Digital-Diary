package com.just.cse.digital_diary.features.admin_office.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.AdminOfficerListDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.viewmodel.AdminOfficerListViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.AdminOfficerListRepository
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
fun AdminOfficers(
    subOfficeId: String,
    repository: AdminOfficerListRepository,
    onExitRequest: () -> Unit,
) {
    val viewModel = remember {
        AdminOfficerListViewModel(
            repository = repository,
            subOfficeId = subOfficeId,
        )
    }
    LaunchedEffect(Unit){
        viewModel.updateTeacherList()
    }

    ProgressBarNSnackBarDecorator(
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading
    ) {
        Scaffold(
            topBar = {
                SimpleTopBar(
                    title = "Admin Officers",
                    navigationIcon = Icons.Default.ArrowBack,
                    onNavigationIconClick = onExitRequest
                )
            }
        ) {
            Box(Modifier.padding(it)) {
                AdminOfficerListDestination(
                    viewModel = viewModel,
                )
            }
        }
    }


}