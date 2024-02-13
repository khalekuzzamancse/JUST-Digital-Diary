package com.just.cse.digital_diary.features.faculty.components.functionalities.department_list

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.DepartmentsList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.state.DepartmentListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.event.DepartmentListEvent

@Composable
internal fun DepartmentListDestination(
    modifier: Modifier = Modifier,
    state: DepartmentListState,
    onEvent: (DepartmentListEvent) -> Unit,
) {
    DepartmentsList(
        modifier = modifier,
        state = state,
        onEvent = onEvent
    )

}