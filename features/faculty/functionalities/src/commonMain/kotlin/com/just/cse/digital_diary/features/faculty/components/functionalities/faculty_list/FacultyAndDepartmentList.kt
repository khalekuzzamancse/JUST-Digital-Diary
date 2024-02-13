package com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.components.functionalities.department_list.DepartmentListDestination
import com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list.viewmodel.FacultiesScreenViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.event.FacultyListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.event.DepartmentListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.repoisitory.DepartmentListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.faculties.repoisitory.FacultyListRepository
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout

/**
 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
fun FacultyAndDepartmentList(
    modifier: Modifier = Modifier,
    facultyListRepository: FacultyListRepository,
    departmentListRepository: DepartmentListRepository,
    onExitRequest:()->Unit,
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
                if (deptId != null){
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
    val showDepartmentList=departmentListState!=null
    backHandler {
        if (showDepartmentList)
        {
            viewModel.clearFacultySelection()
            true
        //consuming the back event to dismiss department list
        }
        else{
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
                if (departmentListState != null){
                    DepartmentListDestination(
                        state = departmentListState,
                        onEvent = onDepartmentListEvent
                    )
                }
            },
            alignment = Alignment.TopStart
        )




}