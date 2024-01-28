package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.DepartmentsList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.viewmodel.DepartmentListDestinationViewModel

@Composable
fun DepartmentDestinationList(
    modifier: Modifier = Modifier,
    viewModel: DepartmentListDestinationViewModel,
    onEmployeeListRequest:(String)->Unit,
) {
    DepartmentsList(
        modifier = modifier,
        onEvent = viewModel::onEvent,
        state = viewModel.state.collectAsState().value.departmentListState
    )
    LaunchedEffect(Unit){
        viewModel.changeFacultyId("01")
        viewModel.selectedDeptId.collect{
            if(it!=null){
                onEmployeeListRequest(it)
            }
        }
    }

}