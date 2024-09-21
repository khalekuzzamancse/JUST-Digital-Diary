package academic.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.newui.SnackNProgressBarDecorator
import common.newui.TwoPaneLayout
import common.newui.TwoPaneNewUIPros
import faculty.ui.department.DepartmentListDestination
import faculty.ui.department.DepartmentListEvent
import faculty.ui.department.DepartmentListState
import faculty.ui.faculty.FacultiesScreenViewModel
import faculty.ui.faculty.FacultyListDestination
import faculty.ui.faculty.FacultyListEvent
import faculty.ui.faculty.FacultyListState

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
 */
@Composable
 fun FacultyAndDepartmentList(
    modifier: Modifier = Modifier,
    viewModel: FacultiesScreenViewModel,
    onExitRequest: () -> Unit,
    isNavRailMode:Boolean,
    onEmployeeListRequest: (String) -> Unit = {},
    backHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {

    val onFacultyEvent: (FacultyListEvent) -> Unit = { event ->
        when (event) {
            is FacultyListEvent.FacultySelected -> {
                viewModel.onFacultySelected(event.index)
            }
        }

    }
    val onDepartmentListEvent: (DepartmentListEvent) -> Unit = { event ->
        when (event) {
            is DepartmentListEvent.DepartmentSelected -> {
                val deptId = viewModel.getDepartmentId(event.index)
                if (deptId != null) {
                    onEmployeeListRequest(deptId)
                }

            }
        }

    }
    val facultyState: FacultyListState = viewModel.facultyListState.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadFacultyList()
    }
    val departmentListState = viewModel.departmentListState.collectAsState().value
    val showDepartmentList = departmentListState != null
    backHandler {
        if (showDepartmentList) {
            viewModel.clearFacultySelection()
            true
            //consuming the back event to dismiss department list
        } else {
            //since department list closed,so only faculty list is opened
            //user click on the back button,so we don't need to consume this  back press
            false
        }

    }
    SnackNProgressBarDecorator(
        snackBarData = viewModel.uiState.collectAsState().value.snackBarData,
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        onSnackBarCloseRequest = viewModel::clearFacultySelection
    ) {
        _FacultyNDepartmentRaw(
            modifier = modifier,
            facultyState = facultyState,
            departmentListState = departmentListState,
            onDepartmentEvent = onDepartmentListEvent,
            onFacultyEvent = onFacultyEvent,
            clearFacultySelection = viewModel::clearFacultySelection,
            onExitRequest = onExitRequest,
            isNavRailMode = isNavRailMode
        )
    }

}

@Composable
private fun _FacultyNDepartmentRaw(
    modifier: Modifier = Modifier,
    facultyState: FacultyListState,
    departmentListState: DepartmentListState?,
    isNavRailMode:Boolean,
    onDepartmentEvent: (DepartmentListEvent) -> Unit,
    clearFacultySelection: () -> Unit,
    onFacultyEvent: (FacultyListEvent) -> Unit,
    onExitRequest: () -> Unit,
) {
    val showDepartmentList = departmentListState != null
    val navigationIcon = if (showDepartmentList) Icons.AutoMirrored.Filled.ArrowBack else {
        if (isNavRailMode)null else Icons.Default.Menu
    }
    val alignment = Alignment.TopStart
    val props = TwoPaneNewUIPros(
        showTopOrRightPane = showDepartmentList,
        alignment = alignment,
        navigationIcon = navigationIcon
    )
    TwoPaneLayout(
        modifier = modifier,
        props = props,
        onNavigationIconClick = if (showDepartmentList) clearFacultySelection else onExitRequest,
        leftPane = {
            FacultyListDestination(
                modifier = Modifier,
                state = facultyState,
                onEvent = onFacultyEvent
            )
        },
        topOrRightPane = {
            if (departmentListState != null) {
                DepartmentListDestination(
                    modifier = Modifier.fillMaxSize(),
                    state = departmentListState,
                    onEvent = onDepartmentEvent
                )
            }
        },

        )

}
