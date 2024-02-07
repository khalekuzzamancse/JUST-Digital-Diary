package com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.components.functionalities.department_list.DepartmentListDestination
import com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list.viewmodel.FacultiesScreenViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.event.FacultyListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.event.DepartmentListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory.DepartmentListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory.FacultyListRepository
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout

@Composable
fun FacultiesInfoDestination(
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
    val onFacultyEvent: (FacultyListEvent) -> Unit = { event ->
        when (event) {
            is FacultyListEvent.FacultySelected -> {
                viewModel.onFacultySelected(event.index)
            }
        }

    }
    val onDepartmentListEvent: (DepartmentListEvent) -> Unit = { event ->
        when (event) {
            is DepartmentListEvent.DepartmentSelected -> {
                val deptId = viewModel.getDepartmentId(event.index)
                if (deptId != null)
                    onEmployeeListRequest(deptId)
            }
        }

    }

    val openDepartmentList = viewModel.uiState.collectAsState().value.openDepartmentListDestination
    val facultyState = viewModel.facultyListState.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadFacultyList()
    }
    WindowSizeDecorator(
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        onCompact = {
            Box(Modifier.fillMaxSize()) {
                FacultyListDestination(
                    modifier = Modifier,
                    state = facultyState,
                    onEvent = onFacultyEvent
                )
                if (openDepartmentList) {
                    viewModel.departmentListState.collectAsState().value?.let {
                        DepartmentListDestination(
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
                    FacultyListDestination(
                        state = facultyState,
                        onEvent = onFacultyEvent
                    )
                },
                topOrRightPane = {
                    viewModel.departmentListState.collectAsState().value?.let {
                        DepartmentListDestination(
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