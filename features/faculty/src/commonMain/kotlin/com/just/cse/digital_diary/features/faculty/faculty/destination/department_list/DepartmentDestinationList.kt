package com.just.cse.digital_diary.features.faculty.faculty.destination.department_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.DepartmentDestinationList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel.DepartmentListDestinationViewModel

@Composable
fun DepartmentList(
    modifier: Modifier=Modifier,
    viewModel: DepartmentListDestinationViewModel,
    onEmployeeListRequest:(String)->Unit
) {
    DepartmentDestinationList(
        modifier = modifier,
        viewModel =viewModel,
        onEmployeeListRequest = onEmployeeListRequest
    )

}