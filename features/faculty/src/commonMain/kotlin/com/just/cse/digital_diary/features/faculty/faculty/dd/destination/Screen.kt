package com.just.cse.digital_diary.features.faculty.faculty.dd.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.faculty.dd.viewmodel.FacultiesScreenViewModel
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.HomeContent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout

@Composable
fun FacultiesScreen(
    onDepartmentSelected: (String) -> Unit = {},
) {
    val viewModel = remember { FacultiesScreenViewModel() }
    LaunchedEffect(Unit) {
        viewModel.selectedDepartmentId.collect {
            if (it != null)
                onDepartmentSelected(it)
        }
    }
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
                        HomeContent()
                    }
                )
                if (openDepartmentList) {
                    DepartmentList(
                        modifier = Modifier.matchParentSize()
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        viewModel = departmentViewModel
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
                            HomeContent()
                        }

                    )
                },
                topOrRightPane = {
                    DepartmentList(viewModel = departmentViewModel)
                }
            )
        }
    )


}