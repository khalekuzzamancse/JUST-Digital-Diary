package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.event.TeachersDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.viewmodel.TeacherListViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list.EmployeeCard
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list.event.TeacherListEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
fun TeacherListDestination(
    modifier: Modifier = Modifier,
    viewModel: TeacherListViewModel,
    onEvent:(TeachersDestinationEvent)->Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.loadTeacherList()
    }
    val teacherList=viewModel.uiState.collectAsState().value.teacherListState.teachers

    ProgressBarNSnackBarDecorator(
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading
    ) {
        Scaffold(
            topBar = {
                SimpleTopBar(
                    title = "Teacher List",
                    onNavigationIconClick = {
                        onEvent(TeachersDestinationEvent.ExitRequest)
                    },
                    navigationIcon = Icons.Default.ArrowBack
                )
            }
        ) {
            if (teacherList.isNotEmpty()) {
                Surface(
                    shadowElevation = 8.dp,
                    modifier = Modifier.padding(it)
                ) {
                    AdaptiveList(
                        modifier = modifier,
                        items = teacherList
                    ) { employee ->
                        EmployeeCard(
                            modifier = Modifier.padding(8.dp),
                            teacher = employee,
                            onCallRequest = {
                                onEvent(TeacherListEvent.CallRequest(employee.phone))
                            },
                            onMessageRequest = {
                                onEvent(TeacherListEvent.MessageRequest(employee.phone))
                            },
                            onEmailRequest = {
                                onEvent(TeacherListEvent.EmailRequest(employee.email))
                            }
                        )
                    }

                }
            }
        }
    }

}