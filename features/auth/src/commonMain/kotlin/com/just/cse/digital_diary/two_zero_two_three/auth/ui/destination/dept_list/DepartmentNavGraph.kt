package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.dept_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.Department
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.DepartmentFakeDB
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.bottom_navigation.BottomNavigationBar
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
    department: Department = DepartmentFakeDB.departmentsOfEngineeringAndTechnology[0]
) {
    Navigator(DepartmentNavHost(department)) { navigator ->
        SlideTransition(navigator = navigator)
    }


}

class DepartmentNavHost(
    val department: Department,
) : Screen {

    @Composable
    override fun Content() {
        val uiState = remember {
            DepartmentMainPageState()
        }
        val selectedDestination = uiState.selectedDestinationIndex.collectAsState().value
        TopNBottomBarDecorator(
            topBarTitle = "Hello",
            topNavigationIcon = Icons.Outlined.ArrowBack,
            onNavigationIconClick = {},
            bottomDestinations = departmentSubDestinations,
            selectedDestinationIndex = selectedDestination,
            onDestinationSelected = uiState::onDestinationSelected
        ) { modifier ->
            when (selectedDestination) {
                0 -> {
                    AnimationDecorator{
                        DepartmentHomeDestinationContent(modifier)
                    }


                }
                1 -> {
                    AnimationDecorator {
                        DepartmentTeacherListDestinationContent(modifier)
                    }
                }
                2 -> {
                    AnimationDecorator {
                        DepartmentStaffListDestinationContent(modifier)
                    }
                }
            }

        }
    }


}

@Composable
fun AnimationDecorator(
    content:@Composable ()->Unit
) {
    val state = remember { MutableTransitionState(false).apply {
        targetState = true
    } }
    val density= LocalDensity.current
    AnimatedVisibility(
        visibleState = state,
        enter = slideInVertically {
            with(density) { -40.dp.roundToPx() }
        },
        exit = slideOutHorizontally (
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(300, easing = FastOutLinearInEasing)
            ),
    ) {
        content()
    }

}
