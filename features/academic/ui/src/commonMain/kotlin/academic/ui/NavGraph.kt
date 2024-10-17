@file:Suppress("className", "functionName")

package academic.ui

import academic.presentationlogic.factory.UiFactory
import academic.ui.admin.UpdateDeptRoute
import academic.ui.admin.UpdateFacultyRoute
import academic.ui.admin.UpdateTeacherRoute
import academic.ui.public_.FacultyNDeptRoute
import academic.ui.public_.FacultyScreenViewModel
import academic.ui.public_.TeachersRoute
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import common.ui.BackButton
import common.ui.NavAnimations

@Composable
fun AcademicNavGraph(
    modifier: Modifier = Modifier,
    token: String?,
    navigationIcon: (@Composable () -> Unit)? = null,
    onEvent: (AcademicModuleEvent) -> Unit
) {

    val navController = rememberNavController()
    val navigator = remember { _Navigator(navController) }
    val processEvent: (AcademicModuleEvent) -> Unit = { event ->
        if (event !is AcademicModuleEvent.AdminEvent)
            onEvent(event)
        else {
            navigator.onNavigationEvent(event)
        }

    }

    NavHost(
        navController = navController,
        startDestination = _NavRoute.FacultyNDeptList.ROUTE,
        modifier = modifier,
        enterTransition = { NavAnimations.Enter.scaleIn() },
        exitTransition = { NavAnimations.Exit.scaleOut() },
        popEnterTransition = { NavAnimations.PopEnter.scaleIn() },
        popExitTransition = { NavAnimations.PopExit.scaleOut() }

    ) {

        composable(route = _NavRoute.FacultyNDeptList.ROUTE) {
            val viewModel = viewModel {
                FacultyScreenViewModel(
                    facultyController = UiFactory.createFacultyController(token),
                    departmentController = UiFactory.createDepartmentsController(token)
                )
            }
            FacultyNDeptRoute(
                viewModel = viewModel,
                navigationIcon = navigationIcon,
                onTeachersRequest =navigator::navigateToTeacherList,
                onEvent = processEvent

            )

        }
        composable(
            route = _NavRoute.TeacherList.ROUTE,
            arguments = _NavRoute.TeacherList.args()
        ) { entry ->
            val deptId = _NavRoute.TeacherList.getId(entry)
            TeachersRoute(
                deptId = deptId ?: "",
                onExitRequest = {
                    navController.popBackStack()
                },
                onEvent = processEvent,
                token = token
            )

        }
        /*
     TODO: Admin Graph
     Make sense to define here, since this module has its own nav host and nav graph.
     These are non-top destinations, so it makes sense to use the back button regardless of the drawer or NavRail.
     Only update features are added here because this module can only generate update events.
     Insertion events are generated in the other modules.
     Update and delete need context, such as an ID, which is why we define update and delete events here.
     As a result, the user can easily update, delete, and see the immediate changes.
     */

        composable(
            route = _NavRoute.UpdateFaculty.ROUTE,
            arguments = _NavRoute.UpdateFaculty.args(),
            enterTransition = { NavAnimations.Enter.slideInVertically() },
            exitTransition = { NavAnimations.Exit.slideOutVertically() },
            popEnterTransition = { NavAnimations.PopEnter.slideInVertically() },
            popExitTransition = { NavAnimations.PopExit.slideOutVertically() }
        ) { entry ->
            val id = _NavRoute.UpdateFaculty.getId(entry)
            if (id != null) {
                UpdateFacultyRoute(
                    facultyId = id,
                    navigationIcon = {
                        BackButton(onClick = navigator::goBack)
                    }
                )
            } else {
                Text("Something went wrong")
            }
        }
        composable(
            route = _NavRoute.UpdateDept.ROUTE,
            arguments = _NavRoute.UpdateDept.args(),
            enterTransition = { NavAnimations.Enter.slideInHorizontally() },
            exitTransition = { NavAnimations.Exit.slideOutHorizontally() },
            popEnterTransition = { NavAnimations.PopEnter.slideInHorizontally() },
            popExitTransition = { NavAnimations.PopExit.slideOutHorizontally() }
        ) { entry ->
            val id = _NavRoute.UpdateDept.getId(entry)
            if (id != null) {
                UpdateDeptRoute(deptId = id, navigationIcon = {
                    BackButton(onClick = navigator::goBack)
                })
            } else {
                Text("Something went wrong")
            }

        }
        composable(
            route = _NavRoute.UpdateTeacher.ROUTE,
            arguments = _NavRoute.UpdateTeacher.args(),
            enterTransition = { NavAnimations.Enter.slideInVertically() },
            exitTransition = { NavAnimations.Exit.slideOutVertically() },
            popEnterTransition = { NavAnimations.PopEnter.slideInVertically() },
            popExitTransition = { NavAnimations.PopExit.slideOutVertically() }
        ) { entry ->
            val id = _NavRoute.UpdateTeacher.getId(entry)
            if (id != null) {
                UpdateTeacherRoute(
                    teacherId = id,
                    navigationIcon = {
                        BackButton(onClick = navigator::goBack)
                    }
                )
            } else {
                Text("Something went wrong")
            }

        }
    }

}

private class _Navigator(
    private val navController: NavHostController,
) {
    fun onNavigationEvent(event: AcademicModuleEvent) {
        when (event) {
            is AcademicModuleEvent.UpdateFacultyRequest ->
                navigate(_NavRoute.UpdateFaculty.createRoute(event.id))

            is AcademicModuleEvent.UpdateDeptRequest ->
                navigate(_NavRoute.UpdateDept.createRoute(event.id))

            is AcademicModuleEvent.UpdateTeacherRequest ->
                navigate(_NavRoute.UpdateTeacher.createRoute(event.id))

            else -> {

            }

        }
    }

    fun goBack() = _try {
        navController.popBackStack()
    }

    private fun _try(block: () -> Unit) {
        try {
            block()
        } catch (_: Exception) {

        }
    }
    fun navigateToTeacherList(deptId: String){
        navigate(_NavRoute.TeacherList.createRoute(deptId))
    }

    private fun navigate(route: String) = _try {
        navController.navigate(route)
    }


}

/**Since they are static fields (objects), using functions instead of storing them helps avoid unnecessary memory usage*/
private sealed class _NavRoute {
    data object FacultyNDeptList {
        const val ROUTE = "faculty_and_dept"
    }

    data object TeacherList : _NavRoute() {
        const val KEY_DEPT = "deptId"
        const val ROUTE = "teacher_list/{$KEY_DEPT}"
        fun args() = listOf(navArgument(KEY_DEPT) { type = NavType.StringType })
        fun getId(entry: NavBackStackEntry): String? = entry.arguments?.getString(KEY_DEPT)
        fun createRoute(deptId: String) = "teacher_list/$deptId"
    }

    data object UpdateFaculty : _NavRoute() {
        private const val KEY_FACULTY = "faculty_id"
        const val ROUTE = "update_faculty/{$KEY_FACULTY}"
        fun args() = listOf(navArgument(KEY_FACULTY) { type = NavType.StringType })
        fun getId(entry: NavBackStackEntry): String? = entry.arguments?.getString(KEY_FACULTY)
        fun createRoute(facultyId: String) = "update_faculty/$facultyId"
    }

    data object UpdateDept : _NavRoute() {
        private const val KEY_DEPT = "dept_id"
        const val ROUTE = "update_department/{$KEY_DEPT}"
        fun args() = listOf(navArgument(KEY_DEPT) { type = NavType.StringType })
        fun getId(entry: NavBackStackEntry): String? = entry.arguments?.getString(KEY_DEPT)
        fun createRoute(deptId: String) = "update_department/$deptId"
    }

    data object UpdateTeacher : _NavRoute() {
        const val KEY_TEACHER = "teacher_id"
        const val ROUTE = "update_teacher/{$KEY_TEACHER}"
        fun args() = listOf(navArgument(KEY_TEACHER) { type = NavType.StringType })
        fun getId(entry: NavBackStackEntry): String? = entry.arguments?.getString(KEY_TEACHER)
        fun createRoute(teacherId: String) = "update_teacher/$teacherId"
    }
}
