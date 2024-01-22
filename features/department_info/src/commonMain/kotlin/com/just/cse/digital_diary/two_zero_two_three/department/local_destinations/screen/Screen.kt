package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.Home
import com.just.cse.digital_diary.two_zero_two_three.employee_list.employee_list.EmployeeList


@Composable
fun DepartmentInfoScreen(
    departmentId: String,
    event: DepartmentModuleEvent,
    onExitRequested: () -> Unit,
) {
    val viewModel = remember { ViewModel() }


    WindowSizeDecorator(
        onCompact = {
            CompactScreenLayout(
                destinationTitle = "Department Information",
                homeContent = {
                    Home(modifier = Modifier)

                },
                employees = viewModel.employees.collectAsState().value,
                onEmployeeListRequest = viewModel::updateEmployee,
                selectedDestinationIndex = viewModel.selectedDestination.collectAsState().value,
                onExitRequested = onExitRequested,
                onCallRequest = event.onCallRequest,
                onEmailRequest =event.onEmailRequest ,
                onMessageRequest = event.onMessageRequest
            )

        },
        onNonCompact = {
            TwoPaneLayout(
                showTopOrRightPane = true,
                props = TwoPaneProps(),
                leftPane = {
                    val destinations = listOf(
                        NavigationItemInfo2(
                            key = "1",
                            label = "Employee",
                            iconText = "EMP"
                        ),
                        NavigationItemInfo2(
                            key = "1",
                            label = "Staff",
                            iconText = "STF"
                        )
                    )
                    NavigationSection(
                        modifier = Modifier,
                        destinations = destinations,
                        onDestinationSelected = viewModel::updateEmployee,
                        selectedDestinationIndex = viewModel.selectedDestination.collectAsState().value,
                    )
                },
                secondaryPaneAnimationState = viewModel.employees.collectAsState().value,
                topOrRightPane = {
                    val employees = viewModel.employees.collectAsState().value
                    if (employees == null) {
                        Home(modifier = Modifier)
                    }
                    if (employees != null) {
                        EmployeeList(
                            modifier = Modifier.fillMaxSize(),
                            employees = employees,
                            onCallRequest = event.onCallRequest,
                            onEmailRequest =event.onEmailRequest ,
                            onMessageRequest = event.onMessageRequest
                        )

                    }

                })


        }
    )

}