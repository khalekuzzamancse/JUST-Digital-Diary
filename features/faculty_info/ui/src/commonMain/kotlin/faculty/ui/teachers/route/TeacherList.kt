package faculty.ui.teachers.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import faculty.ui.teachers.components.employee_list.TeacherList
import faculty.ui.teachers.components.event.TeacherListEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import faculty.domain.teacher.repoisitory.TeacherListRepository
import faculty.ui.route.event.FacultyEvent
import faculty.ui.teachers.route.viewmodel.TeacherListViewModel

@Composable
fun TeacherList(
    modifier: Modifier = Modifier,
    deptId: String,
    repository: TeacherListRepository,
    onEvent: (FacultyEvent)->Unit,
) {
    val viewModel = remember {
        TeacherListViewModel(
            repository, deptId
        )
    }
    LaunchedEffect(Unit) {
        viewModel.loadTeacherList()
    }
    ProgressBarNSnackBarDecorator (
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        snackBarMessage = viewModel.uiState.collectAsState().value.message
    ){
        TeacherList(
            modifier = modifier,
            state = viewModel.uiState.collectAsState().value.teacherListState,
            onEvent = { event ->
                when (event) {
                    is TeacherListEvent.CallRequest -> {
                        onEvent( FacultyEvent.CallRequest(event.number))
                    }

                    is TeacherListEvent.MessageRequest -> {
                        onEvent(FacultyEvent.MessageRequest(event.number))
                    }

                    is TeacherListEvent.EmailRequest -> {
                        onEvent(FacultyEvent.EmailRequest(event.email))
                    }

                }
            }
        )
    }

}