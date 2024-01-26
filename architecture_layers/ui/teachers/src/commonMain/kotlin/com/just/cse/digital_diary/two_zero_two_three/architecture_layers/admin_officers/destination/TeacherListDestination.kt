package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.ListOfTeacher
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.viewmodel.TeacherListViewModel

@Composable
fun TeacherListDestination(
    modifier: Modifier = Modifier,
    viewModel: TeacherListViewModel
) {
    ListOfTeacher(
        modifier = modifier,
        state = viewModel.uiState.collectAsState().value.teacherListState,
        onEvent = viewModel::onEvent,
    )

}