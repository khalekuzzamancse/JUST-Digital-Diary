package academic.ui.public_.teachers

import academic.factory.UiFactory
import academic.ui.public_.teachers.component.TeacherList
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import common.ui.TopBarDecoratorCommon
import common.ui.progressbar.ProgressBarNSnackBarDecorator

@Composable
internal fun TeachersScreen(
    deptId: String,
    onExitRequest: () -> Unit,
    onEvent: (TeacherListEvent)->Unit,
) {
    TopBarDecoratorCommon(
        topNavigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        onNavigationIconClick = onExitRequest,
        topBarTitle = "Teacher List"
    ) {
        val viewModel = remember { UiFactory.createTeachersViewModel() }
        TeacherList(
            modifier = Modifier.padding(it),
            deptId = deptId,
            viewModel = viewModel,
            onEvent=onEvent
        )
    }

}
@Composable
fun TeacherList(
    modifier: Modifier = Modifier,
    deptId: String,
    viewModel: TeacherListViewModel,
    onEvent: (TeacherListEvent)->Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.loadTeacherList()
    }
    ProgressBarNSnackBarDecorator (
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading
    ){
        TeacherList(
            modifier = modifier,
            teachers = viewModel.uiState.collectAsState().value.teachers,
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