@file:Suppress("functionName")

package navigation

import academic.ui.admin.AddDeptRoute
import academic.ui.admin.AddFacultyRoute
import academic.ui.admin.AddTeacherScreen
import academic.ui.admin.UpdateDeptRoute
import academic.ui.admin.UpdateFacultyRoute
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
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import calendar.ui.ui.admin.AddAcademicCalenderScreen
import profile.presentationlogic.ProfileEvent
import schedule.ui.ui.admin.add_class_schedule.AddClassScheduleScreen
import schedule.ui.ui.admin.add_exam_schedule.ExamScheduleAddScreen


fun NavHostController.adminNavGraph(): NavGraph {
    val navController = this
//    NavHost(
//        navController = navController,
//        startDestination = Routes.PROFILE,
//        enterTransition = {
//            scaleIn(initialScale = 0.8f, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
//        },
//        exitTransition = {
//            scaleOut(targetScale = 1.1f, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
//        },
//        popEnterTransition = {
//            scaleIn(initialScale = 1.2f, animationSpec = tween(700)) + fadeIn(animationSpec = tween(700))
//        },
//        popExitTransition = {
//            scaleOut(targetScale = 0.8f, animationSpec = tween(700)) + fadeOut(animationSpec = tween(700))
//        }
//    )

  return  createGraph(startDestination = Routes.EXAM_ROUTINE_UPDATE) {

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
        composable(route = Routes.FACULTY_INSERT) {
            AddFacultyRoute { }
        }
        composable(route = Routes.DEPARTMENT_INSERT) {
            AddDeptRoute { }

        }
        composable(route = Routes.TEACHER_INSERT) {
            AddTeacherScreen()
        }
        composable(route = Routes.FACULTY_UPDATE) {
            UpdateFacultyRoute { }
        }
        composable(route = Routes.DEPARTMENT_UPDATE) {
            UpdateDeptRoute { }
        }
        composable(route = Routes.TEACHER_UPDATE) {

        }

    }
}

fun NavHostController._goBack() {
    try {
        this.popBackStack()
    } catch (_: Exception) {

    }
}

 fun adminNavigationRequest(event: ProfileEvent, navController: NavHostController) {
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

            is ProfileEvent.FacultyInsertRequest -> {
                navController.navigate(Routes.FACULTY_INSERT)
            }


            is ProfileEvent.DepartmentInsertRequest -> {
                navController.navigate(Routes.DEPARTMENT_INSERT)
            }

            is ProfileEvent.TeacherInsertRequest -> {
                navController.navigate(Routes.TEACHER_INSERT)
            }


        }
    } catch (e: Exception) {
        println(e)
    }

}

object Routes {
    const val PROFILE = "Profile"

    // Admin-specific routes
    const val CALENDAR_UPDATE = "CalendarUpdateFeatureNavGraph.ROUTE"
    const val TEACHER_INFO_UPDATE = "TeacherInfoUpdateFeatureNavGraph.ROUTE"
    const val CLASS_ROUTINE_UPDATE = "ClassRoutineUpdateFeatureNavGraph.ROUTE"
    const val EXAM_ROUTINE_UPDATE = "ExamRoutineUpdateFeatureNavGraph.ROUTE"
    const val FACULTY_INSERT = "faculty_insert"
    const val FACULTY_UPDATE = "faculty_update"

    const val DEPARTMENT_INSERT = "department_insert"
    const val DEPARTMENT_UPDATE = "department_update"

    const val TEACHER_INSERT = "teacher_insert"
    const val TEACHER_UPDATE = "teacher_update"
}

@Composable
fun _BackIconDecorator(
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
