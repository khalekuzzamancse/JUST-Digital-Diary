package com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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

/**
 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
fun FacultyAndDepartmentList(
    modifier: Modifier = Modifier,
    facultyListRepository: FacultyListRepository,
    departmentListRepository: DepartmentListRepository,
    onEmployeeListRequest: (String) -> Unit = {},
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
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
    val facultyState = viewModel.facultyListState.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadFacultyList()
    }
    val departmentListState = viewModel.departmentListState.collectAsState().value
    WindowSizeDecorator(
        modifier = modifier,
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        onCompact = {
            if (departmentListState != null) {
                DepartmentListDestination(
                    state = departmentListState,
                    onEvent = onDepartmentListEvent
                )
                backHandler {
                    viewModel.clearFacultySelection()
                }
            } else {
                FacultyListDestination(
                    modifier = Modifier,
                    state = facultyState,
                    onEvent = onFacultyEvent
                )
            }

        },
        onNonCompact = {
            if (departmentListState != null) {
                Row(Modifier.fillMaxWidth()) {
                    FacultyListDestination(
                        modifier = Modifier.weight(1f),
                        state = facultyState,
                        onEvent = onFacultyEvent
                    )
                    DepartmentListDestination(
                        Modifier.weight(1f),
                        state = departmentListState,
                        onEvent = onDepartmentListEvent
                    )
                }
                backHandler {
                    viewModel.clearFacultySelection()
                }
            } else {
                FacultyListDestination(
                    modifier = Modifier,
                    state = facultyState,
                    onEvent = onFacultyEvent
                )
            }

        }
    )


}