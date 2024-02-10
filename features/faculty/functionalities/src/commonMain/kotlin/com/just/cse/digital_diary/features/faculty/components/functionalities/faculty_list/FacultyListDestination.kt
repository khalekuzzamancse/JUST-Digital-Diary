package com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.FacultyList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.state.FacultyListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.event.FacultyListEvent

@Composable
internal fun FacultyListDestination(
    modifier: Modifier = Modifier,
    state: FacultyListState,
    onEvent: (FacultyListEvent) -> Unit,
){
    FacultyList(
        modifier = modifier,
        state = state,
        onEvent = onEvent
    )
}