package com.just.cse.digital_diary.features.faculty.faculty.destination.faculty_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.faculty.viewmodel.FacultiesScreenViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.FacultyList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.event.FacultyDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.DepartmentsListDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.event.DepartmentDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory.DepartmentListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory.FacultyListRepository
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout

@Composable
fun FacultiesScreen(
    facultyListRepository: FacultyListRepository,
    departmentListRepository: DepartmentListRepository,
    onEmployeeListRequest: (String) -> Unit = {},
) {
    val viewModel = remember {
        FacultiesScreenViewModel(
            facultyListRepository = facultyListRepository,
            departmentListRepository = departmentListRepository
        )
    }
    val onFacultyEvent: (FacultyDestinationEvent) -> Unit = { event ->
        when (event) {
            is FacultyDestinationEvent.FacultyListEvent.FacultySelected -> {
                viewModel.onFacultySelected(event.index)
            }

            FacultyDestinationEvent.ExitRequest -> {}
        }

    }
    val onDepartmentListEvent: (DepartmentDestinationEvent) -> Unit = { event ->
        when (event) {
            DepartmentDestinationEvent.DepartmentListEvent.DismissRequest,
            DepartmentDestinationEvent.ExitRequest -> {
                viewModel.clearFacultySelection()
            }
        }

    }

    val openDepartmentList = viewModel.uiState.collectAsState().value.openDepartmentListDestination
    val facultyState = viewModel.facultyListState.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadFacultyList()
    }
    WindowSizeDecorator(
        showProgressBar=viewModel.uiState.collectAsState().value.isLoading,
        onCompact = {
            Box(Modifier.fillMaxSize()) {
                FacultyList(
                    modifier = Modifier,
                    state = facultyState,
                    onEvent = onFacultyEvent
                )
                if (openDepartmentList) {
                    viewModel.departmentListState.collectAsState().value?.let {
                        DepartmentsListDestination(
                            modifier = Modifier.matchParentSize()
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            state = it,
                            onEvent = onDepartmentListEvent
                        )
                    }
                }

            }

        },
        onNonCompact = {
            TwoPaneLayout(
                secondaryPaneAnimationState = false,
                showTopOrRightPane = true,
                leftPane = {
                    FacultyList(
                        state = facultyState,
                        onEvent = onFacultyEvent
                    )
                },
                topOrRightPane = {
                    viewModel.departmentListState.collectAsState().value?.let {
                        DepartmentsListDestination(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            state = it,
                            onEvent = onDepartmentListEvent
                        )
                    }
                }
            )
        }
    )


}