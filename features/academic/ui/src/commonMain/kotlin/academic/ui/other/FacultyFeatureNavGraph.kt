package academic.ui.other

import academic.ui.factory.UiFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import faculty.ui.teachers.TeachersScreen

import faculty.ui.teachers.component.TeacherListEvent

object FacultyFeatureNavGraph {
    private const val DEPT_ID = "deptId"
    private const val FACULTY_SCREEN = "FacultyListScreen"
    private const val TEACHER_LIST="TeacherListScreen"
    private const val TEACHERS_ROUTE = "$TEACHER_LIST/{$DEPT_ID}"

    @Composable
    fun NavHost(
        navController: NavHostController = rememberNavController(),
        onMenuIconClicked: () -> Unit,
        isNavRailMode: Boolean,
        onEvent: (TeacherListEvent) -> Unit
    ) {
        AppTheme {
            _NavHost(
                navController = navController,
                isNavRailMode = isNavRailMode,
                onExitRequest = onMenuIconClicked,
                onEvent = onEvent
            )
        }

    }

    @Composable
    private fun _NavHost(
        navController: NavHostController = rememberNavController(),
        isNavRailMode:Boolean,
        onExitRequest: () -> Unit,
        onEvent: (TeacherListEvent) -> Unit
    ) {

            androidx.navigation.compose.NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = FACULTY_SCREEN
            ) {

                composable(route = FACULTY_SCREEN) {
                    FacultyAndDepartmentList(
                        onEmployeeListRequest = { id ->
                            navController.navigate("$TEACHER_LIST/$id")
                        },
                        backHandler = { onBackPress ->
                            //overriding backhander will prevent it parent to notify that back button is
                            //pressed as a result the parent nav controller may not notify that back button is pressed
                            //as a result the parent nav controller may unable to pop destination automatically
//                            BackHandler(
//                                onBack = {
//                                    val isConsumed = onBackPress()
//                                    if (!isConsumed) {
//                                        onExitRequest()
//                                    }
//                                },
//                            )
                        },
                        onExitRequest =onExitRequest,
                      isNavRailMode = isNavRailMode,
                        viewModel = UiFactory.createFacultyViewModel()
                    )
                }
                composable(
                    route = TEACHERS_ROUTE,
                    arguments = listOf(navArgument(DEPT_ID) { type = NavType.StringType })
                ) { backStackEntry ->
                    val deptID = backStackEntry.arguments?.getString(DEPT_ID)
                 TeachersScreen(
                        deptId = deptID ?: "",
                        onExitRequest = {
                            navController.popBackStack()
                        },
                        onEvent = onEvent
                    )
                }
            }
        }



}