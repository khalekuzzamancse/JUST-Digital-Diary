package com.just.cse.digital_diary.features.faculty.components.functionalities.teacher_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.components.event.FacultyEvent
import com.just.cse.digital_diary.features.faculty.components.functionalities.teacher_list.viewmodel.TeacherListViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.teachers.repoisitory.TeacherListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list.TeacherList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.event.TeacherListEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator

@Composable
fun TeacherList(
    modifier: Modifier = Modifier,
    deptId: String,
    repository: TeacherListRepository,
    onEvent: (FacultyEvent)->Unit,
) {
    val viewModel = remember {
        TeacherListViewModel(
            repository, deptId
        )
    }
    LaunchedEffect(Unit) {
        viewModel.loadTeacherList()
    }
    ProgressBarNSnackBarDecorator (
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        snackBarMessage = viewModel.uiState.collectAsState().value.message
    ){
        TeacherList(
            modifier = modifier,
            state = viewModel.uiState.collectAsState().value.teacherListState,
            onEvent = { event ->
                when (event) {
                    is TeacherListEvent.CallRequest -> {
                        onEvent( FacultyEvent.CallRequest(event.number))
                    }

                    is TeacherListEvent.MessageRequest -> {
                        onEvent(FacultyEvent.MessageRequest(event.number))
                    }

                    is TeacherListEvent.EmailRequest -> {
                        onEvent(FacultyEvent.EmailRequest(event.email))
                    }

                }
            }
        )
    }

}