package academic.ui.public_

import academic.controller_presenter.controller.DepartmentController
import academic.controller_presenter.controller.FacultyController
import academic.controller_presenter.factory.UiFactory
import academic.ui.AcademicModuleEvent
import academic.ui.common.SnackNProgressBarDecorator
import academic.ui.public_.components.Departments
import academic.ui.public_.components.Faculty
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import common.newui.TwoPaneLayout
import common.newui.TwoPaneNewUIPros

@Composable
fun AcademicRoute(
    modifier: Modifier = Modifier,
    token: String?,
    isNavRailMode: Boolean,
    onEvent: (AcademicModuleEvent) -> Unit
) {

    val navController = rememberNavController()

    val viewModel = remember {  FacultyScreenViewModel(
        facultyController = UiFactory.createFacultyController(token),
        departmentController =UiFactory. createDepartmentsController(token)
    ) }

    SnackNProgressBarDecorator(
        isLoading = viewModel.isLoading.collectAsState(false).value,
        snackBarMessage = viewModel.screenMessage.collectAsState(null).value,
    ){
        NavHost(
            navController = navController,
            startDestination = Route.FACULTY_AND_DEPT,
            modifier = Modifier
        ) {

            composable(route = Route.FACULTY_AND_DEPT) {

                FacultyAndDepartmentList(
                    viewModel = viewModel,
                    backHandler = {},
                    isNavRailMode = false,
                    onExitRequest = {},
                    onTeachersRequest = {
                        try {
                            navController.navigate("${Route.TEACHER_LIST}/$it")
                        } catch (_: Exception) {

                        }
                    }
                )
            }
            composable(
                route = Route.TEACHERS_ROUTE,
                arguments = listOf(navArgument(Route.DEPT_ID) { type = NavType.StringType })
            ) { backStackEntry ->
                val deptID = backStackEntry.arguments?.getString(Route.DEPT_ID)
                TeachersScreen(
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


}

private object Route {
    const val TEACHER_LIST = "TeacherListScreen"
    const val FACULTY_AND_DEPT = "FACULTY_AND_DEPT"
    const val DEPT_ID = "deptId"
    const val TEACHERS_ROUTE = "$TEACHER_LIST/{$DEPT_ID}"
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
 * @param isNavRailMode is nullable because in case of NavRail ,there is no Menu Icon will be at the top bar
 *
 *   @param onTeachersRequest This should not handle by controller it should be propagate
 *   to parent so inform that it should navigate
 *
 */
@Composable
fun FacultyAndDepartmentList(
    modifier: Modifier = Modifier,
    viewModel: FacultyScreenViewModel,
    onExitRequest: () -> Unit,
    isNavRailMode: Boolean,
    onTeachersRequest: (String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {
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
    _FacultyNDepartmentRaw(
        modifier = modifier,
        facultyController = viewModel.facultyController,
        departmentController = viewModel.departmentController,
        clearFacultySelection = viewModel::onDeptCloseRequest,
        showDepartmentList = viewModel.showDepartments.collectAsState().value,
        onExitRequest = onExitRequest,
        isNavRailMode = isNavRailMode,
        onTeachersRequest = onTeachersRequest
    )

}

/**
 * @param onTeachersRequest This should not handle by controller it should be propagate
 * to parent so inform that it should navigate
 */
@Composable
private fun _FacultyNDepartmentRaw(
    modifier: Modifier = Modifier,
    facultyController: FacultyController,
    departmentController: DepartmentController,
    onTeachersRequest: (String) -> Unit,
    showDepartmentList: Boolean,
    isNavRailMode: Boolean,
    clearFacultySelection: () -> Unit,
    onExitRequest: () -> Unit,
) {
    val navigationIcon = if (showDepartmentList) Icons.AutoMirrored.Filled.ArrowBack else {
        if (isNavRailMode) null else Icons.Default.Menu
    }

    val props = TwoPaneNewUIPros(
        showTopOrRightPane = showDepartmentList,
        alignment = Alignment.TopStart,
        navigationIcon = navigationIcon
    )
    TwoPaneLayout(
        modifier = modifier,
        props = props,
        onNavigationIconClick = if (showDepartmentList) clearFacultySelection else onExitRequest,
        leftPane = {
            Faculty(
                modifier = Modifier,
                controller = facultyController
            )
        },
        topOrRightPane = {
            if (showDepartmentList) {
                Departments(
                    modifier = Modifier.fillMaxSize(),
                    controller = departmentController,
                    onTeachersRequest = onTeachersRequest
                )
            }
        },

        )

}
