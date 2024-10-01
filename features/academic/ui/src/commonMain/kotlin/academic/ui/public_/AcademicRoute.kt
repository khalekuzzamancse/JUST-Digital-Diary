@file:Suppress("ClassName")
package academic.ui.public_

import academic.controller_presenter.factory.UiFactory
import academic.ui.AcademicModuleEvent
import academic.ui.common.SnackNProgressBarDecorator
import academic.ui.public_.components.Departments
import academic.ui.public_.components.Faculty
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AcademicRoute(
    modifier: Modifier = Modifier,
    token: String?,
    navigationIcon: (@Composable () -> Unit)? = null,
    onEvent: (AcademicModuleEvent) -> Unit
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = _Route.FACULTY_AND_DEPT,
        modifier = Modifier
    ) {

        composable(route = _Route.FACULTY_AND_DEPT) {
            val viewModel = viewModel {
                FacultyScreenViewModel(
                    facultyController = UiFactory.createFacultyController(token),
                    departmentController = UiFactory.createDepartmentsController(token)
                )
            }
            _FacultyNDeptRoute(
                viewModel = viewModel,
                navigationIcon = navigationIcon,
                onTeachersRequest = {
                    try {
                        navController.navigate("${_Route.TEACHER_LIST}/$it")
                    } catch (_: Exception) {

                    }
                }
            )

        }
        composable(
            route = _Route.TEACHERS_ROUTE,
            arguments = listOf(navArgument(_Route.DEPT_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val deptID = backStackEntry.arguments?.getString(_Route.DEPT_ID)
            TeachersRoute(
                deptId = deptID ?: "",
                onExitRequest = {
                    navController.popBackStack()
                },
                onEvent = onEvent,
                token = token
            )

        }

    }

}


private object _Route {
    const val TEACHER_LIST = "TeacherListScreen"
    const val FACULTY_AND_DEPT = "FACULTY_AND_DEPT"
    const val DEPT_ID = "deptId"
    const val TEACHERS_ROUTE = "$TEACHER_LIST/{$DEPT_ID}"
}


@Composable
private fun _FacultyNDeptRoute(
    modifier: Modifier = Modifier,
    viewModel: FacultyScreenViewModel,
    onTeachersRequest: (String) -> Unit,
    navigationIcon: (@Composable () -> Unit)? = null,
) {

    val notShowingDepartment = !(viewModel.showDepartments.collectAsState().value)

    SnackNProgressBarDecorator(
        isLoading = viewModel.isLoading.collectAsState(false).value,
        snackBarMessage = viewModel.screenMessage.collectAsState(null).value,
        navigationIcon = if (notShowingDepartment) navigationIcon else {
            {
                IconButton(onClick = viewModel::onDeptCloseRequest) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                }
            }
        },
        content = {
            FacultyAndDepartmentList(
                viewModel = viewModel,
                backHandler = {},
                onTeachersRequest = onTeachersRequest
            )
        }
    )
}

/**
 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 * In case of android if we use BackHandler{} then back  event is not propagate to it parent
 * composable as a result the parent nav controller will not receive the back button event so it will
 * not pop the destination on back press,that is why,we have to explicitly notify the parent that
 * the back event is consumed or not by return a [Boolean] from the [backHandler] onBackButtonPress lambda

 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 *   @param onTeachersRequest This should not handle by controller it should be propagate
 *   to parent so inform that it should navigate
 *
 */
@Composable
private fun FacultyAndDepartmentList(
    modifier: Modifier = Modifier,
    viewModel: FacultyScreenViewModel,
    onTeachersRequest: (String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {
    val departmentController = viewModel.departmentController
    val facultyController = viewModel.facultyController
    val showDepartments = viewModel.showDepartments.collectAsState().value
    backHandler {
        if (showDepartments) {
            viewModel.onDeptCloseRequest()
            true
            //consuming the back event to dismiss department list
        } else {
            //since department list closed,so only faculty list is opened
            //user click on the back button,so we don't need to consume this  back press
            false
        }

    }

    _Layout(
        showDepartments = showDepartments,
        departmentList = {
            Departments(
                modifier = Modifier.fillMaxSize(),
                controller = departmentController,
                onTeachersRequest = onTeachersRequest
            )
        },
        facultyList = {
            Faculty(
                modifier = Modifier,
                controller = facultyController
            )
        }
    )

}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun _Layout(
    modifier: Modifier = Modifier,
    showDepartments: Boolean,
    facultyList: @Composable () -> Unit,
    departmentList: @Composable () -> Unit
) {


    val windowSize = calculateWindowSizeClass().widthSizeClass
    val compact = WindowWidthSizeClass.Compact
    val medium = WindowWidthSizeClass.Medium
    val expanded = WindowWidthSizeClass.Expanded


    AnimatedContent(windowSize) { window ->
        when (window) {
            compact, medium -> {
                if (showDepartments) {
                    departmentList()
                } else {
                    AnimatedVisibility(
                        modifier = modifier.fillMaxWidth(),
                        enter = fadeIn() + expandIn(),
                        exit = shrinkOut() + fadeOut(), //TODO: fix the animation transition later
                        visible = true
                    ) {
                        facultyList()
                    }
                }


            }


            expanded -> {
                Row(modifier = modifier.fillMaxWidth()) {
                    Box(Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                        facultyList()
                    }
                    if (showDepartments) {
                        Spacer(Modifier.width(12.dp))
                        Box(Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                            departmentList()
                        }

                    }

                }
            }
        }
    }


}

