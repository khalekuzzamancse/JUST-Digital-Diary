@file:Suppress("functionName")

package navigation

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


//fun NavHostController.adminNavGraph(): NavGraph {
//    val navController = this
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

//  return  createGraph(startDestination = Routes.EXAM_ROUTINE_UPDATE) {
//
//        composable(Routes.EXAM_ROUTINE_UPDATE) {
//            _BackIconDecorator(
//                onBackRequest = navController::_goBack,
//            ) {
//                ExamScheduleAddScreen()
//            }
//        }
//        composable(Routes.CLASS_ROUTINE_UPDATE) {
//            _BackIconDecorator(
//                onBackRequest = navController::_goBack,
//            ) {
//                AddClassScheduleScreen()
//            }
//        }
//        composable(Routes.TEACHER_INFO_UPDATE) {
//            _BackIconDecorator(
//                onBackRequest = navController::_goBack,
//            ) {
//                AddTeacherScreen()
//            }
//        }
//        composable(Routes.CALENDAR_UPDATE) {
//            _BackIconDecorator(
//                onBackRequest = navController::_goBack,
//            ) {
//                AddAcademicCalenderScreen()
//            }
//
//        }
//        composable(route = Routes.FACULTY_INSERT) {
//            AddFacultyRoute { }
//        }
//        composable(route = Routes.DEPARTMENT_INSERT) {
//            InsertDeptRoute { }
//
//        }
//        composable(route = Routes.TEACHER_INSERT) {
//            AddTeacherScreen()
//        }
//        composable(route = Routes.FACULTY_UPDATE) {
//            UpdateFacultyRoute { }
//        }
//        composable(route = Routes.UPDATE_DEPT_ROUTE) {
//            UpdateDeptRoute { }
//        }
//        composable(route = Routes.UPDATE_TEACHER) {
//
//        }
//
//    }
//}
//
fun NavHostController._goBack() {
    try {
        this.popBackStack()
    } catch (_: Exception) {

    }
}
//

//
//}

//object Routes {
//    const val PROFILE = "Profile"
//
//    // Admin-specific routes
//    const val CALENDAR_UPDATE = "CalendarUpdateFeatureNavGraph.ROUTE"
//    const val TEACHER_INFO_UPDATE = "TeacherInfoUpdateFeatureNavGraph.ROUTE"
//    const val CLASS_ROUTINE_UPDATE = "ClassRoutineUpdateFeatureNavGraph.ROUTE"
//    const val EXAM_ROUTINE_UPDATE = "ExamRoutineUpdateFeatureNavGraph.ROUTE"
//    const val FACULTY_INSERT = "faculty_insert"
//    const val FACULTY_UPDATE = "faculty_update"
//
//    const val DEPARTMENT_INSERT = "department_insert"
//    const val UPDATE_DEPT_ROUTE = "department_update"
//
//    const val TEACHER_INSERT = "teacher_insert"
//    const val UPDATE_TEACHER = "teacher_update"
//}

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
