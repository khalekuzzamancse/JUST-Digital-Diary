package faculty.ui.teachers.teacherlist.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import common.ui.progressbar.ProgressBarNSnackBarDecorator
import faculty.ui.teachers.teacherlist.component.TeacherList
import faculty.domain.teacher.repoisitory.TeacherListRepository
import faculty.ui.teachers.teacherlist.component.TeacherListEvent

@Composable
fun TeacherList(
    modifier: Modifier = Modifier,
    deptId: String,
    repository: TeacherListRepository,
    onEvent: (TeacherListEvent)->Unit,
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
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading
    ){
        TeacherList(
            modifier = modifier,
            state = viewModel.uiState.collectAsState().value.teacherListState,
            onEvent = { event ->
                when (event) {
                    is TeacherListEvent.CallRequest -> {
                        onEvent( TeacherListEvent.CallRequest(event.number))
                    }

                    is TeacherListEvent.MessageRequest -> {
                        onEvent(TeacherListEvent.MessageRequest(event.number))
                    }

                    is TeacherListEvent.EmailRequest -> {
                        onEvent(TeacherListEvent.EmailRequest(event.email))
                    }

                }
            }
        )
    }

}