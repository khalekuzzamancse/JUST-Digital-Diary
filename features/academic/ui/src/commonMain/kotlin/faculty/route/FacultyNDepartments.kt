package faculty.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import faculty.di.FacultyComponentProvider

/**
 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 * In case of android if we use BackHandler{} then back  event is not propagate to it parent
 * composable as a result the parent nav controller will not receive the back button event so it will
 * not pop the destination on back press,that is why,we have to explicitly notify the parent that
 * the back event is consumed or not by return a [Boolean] from the [backHandler] onBackButtonPress lambda
 *
 */
@Composable
 fun FacultyNDepartments(
    onTeacherListRequest: (String) -> Unit = {},
    isNavRailMode:Boolean,
    onExitRequest:()->Unit,
    backHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {
    FacultyAndDepartmentList(
        modifier = Modifier,
        facultyListRepository = FacultyComponentProvider.getFacultyRepository(),
        departmentListRepository = FacultyComponentProvider.getDepartListRepository(),
        onEmployeeListRequest = onTeacherListRequest,
        backHandler=backHandler,
        onExitRequest = onExitRequest,
        isNavRailMode = isNavRailMode
    )


}