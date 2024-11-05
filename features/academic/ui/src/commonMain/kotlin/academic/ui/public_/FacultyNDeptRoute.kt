@file:Suppress("ClassName")

package academic.ui.public_

import academic.ui.AcademicModuleEvent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import common.ui.SnackNProgressBarDecorator
import common.ui.TwoPaneLayout


@Composable
internal fun FacultyNDeptRoute(
    modifier: Modifier = Modifier,
    viewModel: FacultyScreenViewModel,
    onTeachersRequest: (String) -> Unit,
    onEvent: (AcademicModuleEvent) -> Unit,
    navigationIcon: (@Composable () -> Unit)? = null,
) {

    val notShowingDepartment = !(viewModel.showDepartments.collectAsState().value)



    SnackNProgressBarDecorator(
        isLoading = viewModel.isLoading.collectAsState(false).value,
        message = viewModel.screenMessage.collectAsState(null).value,
        navigationIcon = if (notShowingDepartment) navigationIcon else {
            {
                IconButton(onClick = viewModel::onDeptCloseRequest) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                }
            }
        },
        content = {
            _FacultyAndDepartmentList(
                viewModel = viewModel,
                onTeachersRequest = onTeachersRequest,
                onEvent = onEvent,
                backHandler = {}
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
private fun _FacultyAndDepartmentList(
    viewModel: FacultyScreenViewModel,
    onTeachersRequest: (String) -> Unit,
    onEvent: (AcademicModuleEvent) -> Unit,
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

    TwoPaneLayout(
        showSecondPane = showDepartments,
        leftPane = {
            Faculty(
                isAdmin = false,
                modifier = Modifier,
                controller = facultyController,
                onEvent = onEvent
            )
        },
        rightOrTopPane = {
            Departments(
                isAdmin = false,
                modifier = Modifier.fillMaxSize(),
                controller = departmentController,
                onTeachersRequest = onTeachersRequest,
                onEvent = onEvent
            )
        }

    )

}
