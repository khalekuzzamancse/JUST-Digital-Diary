@file:Suppress("functionName")

package profile.ui

import academic.ui.admin.AddTeacherScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import calendar.ui.ui.admin.AddAcademicCalenderScreen
import profile.presentationlogic.ProfileEvent
import schedule.ui.ui.admin.add_class_schedule.AddClassScheduleScreen
import schedule.ui.ui.admin.add_exam_schedule.ExamScheduleAddScreen

@Composable
fun ProfileNavHost(
    token: String?,
    navigationIcon: (@Composable () -> Unit)? = null,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.PROFILE,
        enterTransition = {
            scaleIn(initialScale = 0.8f, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
        },
        exitTransition = {
            scaleOut(targetScale = 1.1f, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
        },
        popEnterTransition = {
            scaleIn(initialScale = 1.2f, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
        },
        popExitTransition = {
            scaleOut(targetScale = 0.8f, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
        }
    ) {
        composable(Routes.PROFILE) {
            _TopBarDecorator(
                navigationIcon = navigationIcon
            ) {
                ProfileRoute(
                    token = token,
                    onEvent = { event ->
                        _onNavigationEvent(event, navController)
                    }
                )
            }

        }
        composable(Routes.EXAM_ROUTINE_UPDATE) {
            _BackIconDecorator(
                onBackRequest = navController::_goBack,
            ) {
                ExamScheduleAddScreen()
            }
        }
        composable(Routes.CLASS_ROUTINE_UPDATE) {
            _BackIconDecorator(
                onBackRequest = navController::_goBack,
            ) {
                AddClassScheduleScreen()
            }
        }
        composable(Routes.TEACHER_INFO_UPDATE) {
            _BackIconDecorator(
                onBackRequest = navController::_goBack,
            ) {
                AddTeacherScreen()
            }
        }
        composable(Routes.CALENDAR_UPDATE) {
            _BackIconDecorator(
                onBackRequest = navController::_goBack,
            ) {
                AddAcademicCalenderScreen()
            }

        }
    }
}

private fun NavHostController._goBack() {
    try {
        this.popBackStack()
    } catch (_: Exception) {

    }
}

private fun _onNavigationEvent(event: ProfileEvent, navController: NavHostController) {
    try {
        when (event) {

            is ProfileEvent.NavigateToCalendarUpdate -> {
                navController.navigate(Routes.CALENDAR_UPDATE)
            }


            is ProfileEvent.NavigateToExamRoutineUpdate -> {
                navController.navigate(Routes.EXAM_ROUTINE_UPDATE)
            }

            is ProfileEvent.NavigateToClassRoutineUpdate -> {
                navController.navigate(Routes.CLASS_ROUTINE_UPDATE)
            }

            is ProfileEvent.NavigateToTeacherInfoUpdate -> {
                navController.navigate(Routes.TEACHER_INFO_UPDATE)
            }


        }
    } catch (_: Exception) {
    }

}

private object Routes {
    const val PROFILE = "Profile"

    // Admin-specific routes
    const val CALENDAR_UPDATE = "CalendarUpdateFeatureNavGraph.ROUTE"
    const val TEACHER_INFO_UPDATE = "TeacherInfoUpdateFeatureNavGraph.ROUTE"
    const val CLASS_ROUTINE_UPDATE = "ClassRoutineUpdateFeatureNavGraph.ROUTE"
    const val EXAM_ROUTINE_UPDATE = "ExamRoutineUpdateFeatureNavGraph.ROUTE"
}

@Composable
private fun _BackIconDecorator(
    onBackRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    _TopBarDecorator(
        navigationIcon = {
            IconButton(
                onClick = onBackRequest
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Drawer Icon"
                )
            }
        },
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun _TopBarDecorator(
    navigationIcon: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    if (navigationIcon != null)
                        navigationIcon()
                },
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

}
