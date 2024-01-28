package com.just.cse.digital_diary.features.faculty.faculty.destination.faculty_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.faculty.destination.department_list.DepartmentList
import com.just.cse.digital_diary.features.faculty.faculty.viewmodel.FacultiesScreenViewModel
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout

@Composable
fun FacultiesScreen(
    onEmployeeListRequest: (String) -> Unit = {},
    onExitRequest: () -> Unit,
) {
    val viewModel = remember { FacultiesScreenViewModel() }

    val facultyViewModel = viewModel.facultyViewModel
    val departmentViewModel = viewModel.departmentViewModel
    val openDepartmentList = viewModel.uiState.collectAsState().value.openDepartmentListDestination

    WindowSizeDecorator(
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        onCompact = {
            Box(Modifier) {
                FacultyListDestination(
                    viewModel = facultyViewModel,
                    homeContent = {
                        //  HomeContent()
                    },
                    onExitRequest = onExitRequest
                )
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
                    FacultyListDestination(
                        viewModel = facultyViewModel,
                        homeContent = {
                            //   HomeContent()
                        },
                        onExitRequest = onExitRequest

                    )
                },
                topOrRightPane = {
                    DepartmentList(viewModel = departmentViewModel, onEmployeeListRequest = onEmployeeListRequest)
                }
            )
        }
    )


}