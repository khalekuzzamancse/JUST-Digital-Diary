package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.Home


@Composable
fun Screen(
    departmentId:String,
    onExitRequested:()->Unit
) {
    val viewModel= remember { ViewModel() }
    WindowSizeDecorator(
        onCompact = {
            CompactScreenLayout(
                destinationTitle = "Department of CSE",
                homeContent = {
                    Home(modifier = Modifier)

                },
                employees = viewModel.employees.collectAsState().value,
                onEmployeeListRequest = viewModel::updateEmployee,
                selectedDestinationIndex = viewModel.selectedDestination.collectAsState().value,
                onExitRequested = onExitRequested
            )

        },
        onNonCompact = {
            NonCompactScreenLayout(
                homeContent ={
                    Home(modifier = Modifier)
                },
                employees = viewModel.employees.collectAsState().value,
                onEmployeeListRequest = viewModel::updateEmployee,
                onEmployeeListDismissRequest = viewModel::clearEmployeeList,
                selectedDestinationIndex = viewModel.selectedDestination.collectAsState().value
            )
        }
    )

}