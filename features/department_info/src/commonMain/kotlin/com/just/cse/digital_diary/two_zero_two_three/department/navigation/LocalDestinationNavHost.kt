package com.just.cse.digital_diary.two_zero_two_three.department.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.department.common.AnimateVisibilityDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.common.TopNBottomBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home.departmentSubDestinations
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home.Home
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.staff_list.DepartmentStaffListDestinationContent
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.teacher_list.DepartmentTeacherListDestinationContent
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.DepartmentFakeDB
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
                    Home(
                        modifier = modifier
                    )
                }


            }

            1 -> {
                AnimateVisibilityDecorator {
                    DepartmentTeacherListDestinationContent(
                        modifier = modifier,
                        teachers = DepartmentInfoRepository.getTeacherList("1")
                    )
                }
            }

            2 -> {
                AnimateVisibilityDecorator {
                    DepartmentStaffListDestinationContent(
                        modifier = modifier,
                        staffs = DepartmentInfoRepository.getTeacherList("2")
                    )
                }
            }
        }

    }
}
