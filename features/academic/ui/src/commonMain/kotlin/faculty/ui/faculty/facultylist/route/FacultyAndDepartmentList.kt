package faculty.ui.faculty.facultylist.route

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.ui.layout.TwoPaneLayout
import faculty.domain.department.repoisitory.DepartmentListRepository
import faculty.domain.faculties.repoisitory.FacultyListRepository
import faculty.ui.department.departmentlist.DepartmentListDestination
import faculty.ui.department.departmentlist.DepartmentListEvent
import faculty.ui.faculty.facultylist.components.FacultyListDestination
import faculty.ui.faculty.facultylist.components.FacultyListEvent


/**
 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
fun FacultyAndDepartmentList(
    modifier: Modifier = Modifier,
    facultyListRepository: FacultyListRepository,
    departmentListRepository: DepartmentListRepository,
    onExitRequest: () -> Unit,
    onEmployeeListRequest: (String) -> Unit = {},
    backHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {
    val viewModel = remember {
        FacultiesScreenViewModel(
            facultyListRepository = facultyListRepository,
            departmentListRepository = departmentListRepository
        )
    }
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
    val facultyState = viewModel.facultyListState.collectAsState().value
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
    TwoPaneLayout(
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        snackBarMessage = viewModel.uiState.collectAsState().value.message,
        modifier = modifier,
        navigationIcon = if (showDepartmentList) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu,
        onNavigationIconClick = if (showDepartmentList) viewModel::clearFacultySelection else onExitRequest,
        showTopOrRightPane = showDepartmentList,
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
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)),
                    state = departmentListState,
                    onEvent = onDepartmentListEvent
                )
            }
        },
        alignment = Alignment.TopStart
    )


}