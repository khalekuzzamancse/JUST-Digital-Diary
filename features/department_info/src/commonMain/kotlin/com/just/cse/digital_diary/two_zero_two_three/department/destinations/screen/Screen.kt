package com.just.cse.digital_diary.two_zero_two_three.department.destinations.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.destinations.event.DepartmentInfoEvent
import com.just.cse.digital_diary.two_zero_two_three.department.destinations.home.Home
import com.just.cse.digital_diary.two_zero_two_three.department.destinations.viewmodel.ViewModel


@Composable
fun DepartmentInfoScreen(
    departmentId: String,
    onEvent: (DepartmentInfoEvent) -> Unit,
) {
    val viewModel = remember { ViewModel(departmentId) }
    WindowSizeDecorator(
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        snackBarMessage = viewModel.uiState.collectAsState().value.message,
        onCompact = {
            CompactScreenLayout(
                homeContent = {
                    Home(modifier = Modifier)
                },
                onEvent = viewModel::onEvent,
                decoratorState = viewModel.uiState.collectAsState().value.decoratorState,
                teacherListViewModel = viewModel.teacherListViewModel
            )

        },
        onNonCompact = {
            Text("Not Implemented Yet.....")
        }
    )

}