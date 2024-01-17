package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations

import androidx.compose.animation.AnimatedContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.department.common.TopNBottomBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home.departmentSubDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private class DepartmentMainPageState {
    private val _selectedDestinationIndex = MutableStateFlow(0)
    val selectedDestinationIndex = _selectedDestinationIndex.asStateFlow()
    fun onDestinationSelected(index: Int) {
        _selectedDestinationIndex.update { index }
    }

}
@Composable
internal fun CompactScreenLayout(
    homeContent:( @Composable ()->Unit ),
    teacherList:( @Composable ()->Unit ),
    staffList:( @Composable ()->Unit ),
    onExitRequested:()->Unit,
) {

    val uiState = remember {
        DepartmentMainPageState()
    }
    val selectedDestination = uiState.selectedDestinationIndex.collectAsState().value
    TopNBottomBarDecorator(
        topBarTitle = "CSE",
        topNavigationIcon = if (selectedDestination == 0) Icons.Outlined.ArrowBack else null,
        onNavigationIconClick = onExitRequested,
        bottomDestinations = departmentSubDestinations,
        selectedDestinationIndex = selectedDestination,
        onDestinationSelected = uiState::onDestinationSelected
    ) { modifier ->
        AnimatedContent(
            modifier = modifier,
            targetState = selectedDestination
        ){selectedDestination->
            when (selectedDestination) {
                0 -> {
                    homeContent()
                }

                1 -> {
                    teacherList()
                }

                2 -> {
                    staffList()
                }
            }
        }



    }
}
