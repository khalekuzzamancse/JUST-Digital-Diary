package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.dept_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.DepartmentFakeDB
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.employees
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
All department have the shared top and bottom bar.
they must maintain a separate backstack means navigator

 */
class DepartmentMainPageState {
    private val _selectedDestinationIndex = MutableStateFlow(0)
    val selectedDestinationIndex = _selectedDestinationIndex.asStateFlow()
    fun onDestinationSelected(index: Int) {
        _selectedDestinationIndex.update { index }
    }

}

@Composable
fun DepartmentNavGraph(
    departmentId: String = "",
    onNavigationIconClick: () -> Unit,
) {
    Navigator(
        DepartmentNavHost(
            departmentId = departmentId,
            onNavigationIconClick = onNavigationIconClick)
    ) { navigator ->
        SlideTransition(navigator = navigator)
    }


}

class DepartmentNavHost(
   val onNavigationIconClick: () -> Unit,
    private val departmentId: String,
) : Screen {
    @Composable
    override fun Content() {
        val deptInfo = DepartmentFakeDB.getDepartmentById(departmentId)
        val uiState = remember {
            DepartmentMainPageState()
        }
        val selectedDestination = uiState.selectedDestinationIndex.collectAsState().value
        TopNBottomBarDecorator(
            topBarTitle = deptInfo?.shortName ?: "",
            topNavigationIcon = if (selectedDestination == 0) Icons.Outlined.ArrowBack else null,
            onNavigationIconClick = onNavigationIconClick,
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


}

@Composable
fun AnimateVisibilityDecorator(
    content: @Composable () -> Unit
) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    val density = LocalDensity.current
    AnimatedVisibility(
        visibleState = state,
        enter = slideInHorizontally {
            with(density) { 400.dp.roundToPx() }
        },
        exit = slideOutHorizontally(),
    ) {
        content()
    }

}
