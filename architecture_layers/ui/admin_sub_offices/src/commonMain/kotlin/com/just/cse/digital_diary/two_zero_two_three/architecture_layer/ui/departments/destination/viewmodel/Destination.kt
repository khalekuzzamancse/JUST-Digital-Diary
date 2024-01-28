package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.DepartmentsList

@Composable
fun DepartmentDestinationList(
    modifier: Modifier = Modifier,
    viewModel: DepartmentListDestinationViewModel
) {
    DepartmentsList(
        modifier = modifier,
        onEvent = viewModel::onEvent,
        state = viewModel.state.collectAsState().value.departmentListState
    )

}