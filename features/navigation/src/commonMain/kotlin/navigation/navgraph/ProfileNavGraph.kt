
package navigation.navgraph

import academic.ui.admin.InsertDeptRoute
import academic.ui.admin.InsertFacultyRoute
import academic.ui.admin.InsertTeacherRoute
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import calendar.ui.admin.InsertAcademicCalenderRoute
import common.ui.animation.NavAnimations
import navigation.NavigationFactory
import navigation._BackIconDecorator
import navigation._goBack
import profile.presentationlogic.ProfileEvent
import profile.ui.ProfileNavHost
import schedule.ui.admin.AddClassScheduleScreen
import schedule.ui.admin.ExamScheduleAddScreen

//Though profile will generate the admin event,thus it make sense to define the graph here
//because now profile module need not to depend on different other features modules
//Since this module already have dependencies of other feature modules that is why defining here
//Defining a separate nav graph for profile module because it has non top destination that should
// have back button and should manage it own back stack
private object ProfileRoutes {
    const val PROFILE_NAV_GRAPH = "profile_nav_graph"
    const val INSERT_FACULTY = "insert_faculty"
    const val INSERT_DEPT = "insert_department"
    const val INSERT_TEACHER = "insert_teacher"
    const val UPDATE_EXAM_SCHEDULE = "update_exam_schedule"
    const val UPDATE_CLASS_SCHEDULE = "update_class_schedule"
    const val UPDATE_ACADEMIC_CALENDER = "update_academic_calendar"
}

@Composable
fun ProfileNavGraph(
    navigationIcon: (@Composable () -> Unit)?,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = ProfileRoutes.PROFILE_NAV_GRAPH,  // Use the uppercase route
        enterTransition = { NavAnimations.Enter.slideInHorizontally() },
        exitTransition = { NavAnimations.Exit.slideOutHorizontally() },
        popEnterTransition = { NavAnimations.PopEnter.slideInHorizontally() },
        popExitTransition = { NavAnimations.PopExit.slideOutHorizontally() }
    ) {
        // Profile screen
        composable(ProfileRoutes.PROFILE_NAV_GRAPH) {
            ProfileNavHost(
                token = NavigationFactory.token.value,
                navigationIcon = navigationIcon,
                onEvent = { event ->
                    try {
                        when (event) {
                            is ProfileEvent.CalendarUpdate -> {
                                navController.navigate(ProfileRoutes.UPDATE_ACADEMIC_CALENDER)
                            }
                            is ProfileEvent.ExamRoutineUpdate -> {
                                navController.navigate(ProfileRoutes.UPDATE_EXAM_SCHEDULE)
                            }
                            is ProfileEvent.ClassRoutineUpdate -> {
                                navController.navigate(ProfileRoutes.UPDATE_CLASS_SCHEDULE)
                            }
                            is ProfileEvent.InsertFacultyRequest -> {
                                navController.navigate(ProfileRoutes.INSERT_FACULTY)
                            }
                            is ProfileEvent.InsertDepartmentRequest -> {
                                navController.navigate(ProfileRoutes.INSERT_DEPT)
                            }
                            is ProfileEvent.InsertTeacherRequest -> {
                                navController.navigate(ProfileRoutes.INSERT_TEACHER)
                            }
                            else -> {}
                        }
                    } catch (e: Exception) {
                        println(e)
                    }
                }
            )
        }

        // Admin routes (Insert Faculty, Department, and Teacher)
        composable(route = ProfileRoutes.INSERT_FACULTY) {
            _BackIconDecorator(onBackRequest = navController::_goBack) {
                InsertFacultyRoute { }
            }
        }
        composable(route = ProfileRoutes.INSERT_DEPT) {
            _BackIconDecorator(onBackRequest = navController::_goBack) {
                InsertDeptRoute { }
            }
        }
        composable(route = ProfileRoutes.INSERT_TEACHER) {
            _BackIconDecorator(onBackRequest = navController::_goBack) {
                InsertTeacherRoute()
            }
        }

        // Update exam schedule, class schedule, and academic calendar
        composable(ProfileRoutes.UPDATE_EXAM_SCHEDULE) {
            _BackIconDecorator(onBackRequest = navController::_goBack) {
                ExamScheduleAddScreen()
            }
        }
        composable(ProfileRoutes.UPDATE_CLASS_SCHEDULE) {
            _BackIconDecorator(onBackRequest = navController::_goBack) {
                AddClassScheduleScreen()
            }
        }
        composable(ProfileRoutes.UPDATE_ACADEMIC_CALENDER) {
            _BackIconDecorator(onBackRequest = navController::_goBack) {
                InsertAcademicCalenderRoute()
            }
        }
    }
}

