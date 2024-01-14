package com.just.cse.digital_diary.two_zero_two_three.department.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.department.AnimateVisibilityDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.TopNBottomBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.departmentSubDestinations
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.destinations.DepartmentHomeDestinationContent
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.destinations.DepartmentStaffListDestinationContent
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.destinations.DepartmentTeacherListDestinationContent
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.DepartmentFakeDB
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.employees
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


//
/*
All department have the shared top and bottom bar.
they must maintain a separate backstack means navigator

 */
internal class DepartmentMainPageState {
    private val _selectedDestinationIndex = MutableStateFlow(0)
    val selectedDestinationIndex = _selectedDestinationIndex.asStateFlow()
    fun onDestinationSelected(index: Int) {
        _selectedDestinationIndex.update { index }
    }

}

//prevent outside module to access it

@Composable
internal fun DepartmentModuleLocalNavGraph(
    departmentId: String,
    onExitRequested:()->Unit,
) {
    val deptInfo = DepartmentFakeDB.getDepartmentById(departmentId)
    val uiState = remember {
        DepartmentMainPageState()
    }
    val selectedDestination = uiState.selectedDestinationIndex.collectAsState().value
    TopNBottomBarDecorator(
        topBarTitle = deptInfo?.shortName ?: "",
        topNavigationIcon = if (selectedDestination == 0) Icons.Outlined.ArrowBack else null,
        onNavigationIconClick = onExitRequested,
        bottomDestinations = departmentSubDestinations,
        selectedDestinationIndex = selectedDestination,
        onDestinationSelected = uiState::onDestinationSelected
    ) { modifier ->
        when (selectedDestination) {
            0 -> {
                AnimateVisibilityDecorator {
                    DepartmentHomeDestinationContent(
                        modifier = modifier
                    )
                }


            }

            1 -> {
                AnimateVisibilityDecorator {
                    DepartmentTeacherListDestinationContent(
                        modifier = modifier,
                        teachers = employees
                    )
                }
            }

            2 -> {
                AnimateVisibilityDecorator {
                    DepartmentStaffListDestinationContent(
                        modifier = modifier,
                        staffs = employees
                    )
                }
            }
        }

    }
}
