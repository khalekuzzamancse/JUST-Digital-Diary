package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list.ListOfTeacher
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.viewmodel.TeacherListViewModel

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
    LaunchedEffect(Unit){
        viewModel.setDepartmentId("01")
    }


}