package academic.ui.public_

import academic.controller_presenter.factory.UiFactory
import academic.controller_presenter.model.TeacherModel
import academic.ui.AcademicModuleEvent
import academic.ui.common.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import common.ui.AdaptiveList
import common.ui.GenericEmployeeCard
import common.ui.TopBarDecoratorCommon


@Composable
internal fun TeachersRoute(
    deptId: String,
    token:String?,
    onExitRequest: () -> Unit,
    onEvent: (AcademicModuleEvent) -> Unit
    //TODO: event should go out,it should not handle by controller
) {
    val viewModel = viewModel { TeacherListViewModel(UiFactory.createTeachersController(token)) }
    val controller = viewModel.controller
    val teachers = controller.teachers.collectAsState().value
    LaunchedEffect(Unit) {
        controller.fetch(deptId)
    }
    SnackNProgressBarDecorator(
        isLoading = viewModel.isLoading.collectAsState(false).value,
        snackBarMessage = viewModel.screenMessage.collectAsState(null).value
    ){
        TopBarDecoratorCommon(
            topNavigationIcon = Icons.AutoMirrored.Default.ArrowBack,
            onNavigationIconClick = onExitRequest,
            topBarTitle = "Teacher List"
        ) {

            _TeacherList(
                modifier = Modifier.padding(it),
                teachers = teachers,
                onEvent = onEvent
            )
        }
    }


}

@Composable
private fun _TeacherList(
    modifier: Modifier = Modifier,
    teachers: List<TeacherModel>,
    onEvent: (AcademicModuleEvent) -> Unit
) {

    AdaptiveList(
        modifier = modifier,
        items = teachers
    ) { employee ->
        _EmployeeCard(
            modifier = Modifier.padding(8.dp),
            teacher = employee,
            onCallRequest = {
                onEvent(AcademicModuleEvent.CallRequest(employee.phone))
            },
            onMessageRequest = {
                onEvent(AcademicModuleEvent.MessageRequest(employee.phone))
            },
            onEmailRequest = {
                onEvent(AcademicModuleEvent.EmailRequest(employee.email))
            }
        )


    }
}

@Composable
private fun _EmployeeCard(
    modifier: Modifier,
    teacher: TeacherModel,
    expandMode: Boolean = true,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {

    GenericEmployeeCard(
        modifier = modifier,
        name = teacher.name,
        profileImageUrl = teacher.profileImageLink,
        expandMode = expandMode,
        onCallRequest = onCallRequest,
        onEmailRequest = onEmailRequest,
        onMessageRequest = onMessageRequest,
        details = {
            EmployeeDetails(
                teacher = teacher,
                modifier = Modifier
            )
        })


}


@Composable
private fun EmployeeDetails(
    modifier: Modifier,
    teacher: TeacherModel
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = teacher.achievements,
            style = CardTypography.subTitle
        )
        Text(
            text = teacher.designations,
            style = CardTypography.title2
        )
        Text(
            text = teacher.email,
            style = CardTypography.contactStyle
        )
        Text(
            text = teacher.additionalEmail,
            style = CardTypography.contactStyle
        )
        Text(
            text = teacher.phone,
            style = CardTypography.contactStyle
        )


    }
}

