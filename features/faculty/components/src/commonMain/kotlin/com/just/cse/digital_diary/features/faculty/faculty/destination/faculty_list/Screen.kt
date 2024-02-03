package com.just.cse.digital_diary.features.faculty.faculty.destination.faculty_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.faculty.destination.department_list.DepartmentList
import com.just.cse.digital_diary.features.faculty.faculty.viewmodel.FacultiesScreenViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.FacultyList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory.DepartmentListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory.FacultyListRepository
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
fun FacultiesScreen(
    facultyListRepository: FacultyListRepository,
    departmentListRepository: DepartmentListRepository,
    onEmployeeListRequest: (String) -> Unit = {},
    onExitRequest: () -> Unit,
    ) {
    val viewModel = remember {
        FacultiesScreenViewModel(
            facultyListRepository = facultyListRepository,
            departmentListRepository = departmentListRepository
        )
    }

    val facultyViewModel = viewModel.facultyViewModel
    val departmentViewModel = viewModel.departmentViewModel
    val openDepartmentList = viewModel.uiState.collectAsState().value.openDepartmentListDestination

    WindowSizeDecorator(
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        onCompact = {
            Box(Modifier.fillMaxSize()) {
                Scaffold(
                    topBar = {
                        SimpleTopBar(
                            title = "Faculty List",
                            navigationIcon = Icons.Default.Menu,
                            onNavigationIconClick = onExitRequest
                        )
                    }
                ) {
                    FacultyList(
                        modifier = Modifier.padding(it),
                        state = facultyViewModel.state.value.facultyListState,
                        onEvent = facultyViewModel::onEvent,
                    )
                }

                if (openDepartmentList) {
                    DepartmentList(
                        modifier = Modifier.matchParentSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        viewModel = departmentViewModel,
                        onEmployeeListRequest = onEmployeeListRequest
                    )
                }

            }

        },
        onNonCompact = {
            TwoPaneLayout(
                secondaryPaneAnimationState = false,
                showTopOrRightPane = true,
                leftPane = {
                    FacultyList(
                        state = facultyViewModel.state.value.facultyListState,
                        onEvent = facultyViewModel::onEvent,
                    )
                },
                topOrRightPane = {
                    DepartmentList(
                        viewModel = departmentViewModel,
                        onEmployeeListRequest = onEmployeeListRequest
                    )
                }
            )
        }
    )


}