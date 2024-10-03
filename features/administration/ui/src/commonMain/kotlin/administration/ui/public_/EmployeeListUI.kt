package administration.ui.public_

import administration.controller_presenter.factory.UiFactory
import administration.controller_presenter.model.AdminOfficerModel
import administration.ui.common.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import common.ui.AdaptiveList
import common.ui.GenericEmployeeCard
import common.ui.TopBarDecoratorCommon


/*
It this function must be called with a list.
the user have to choice where to place this composable,
will it place in a separate screen or it it place in a  pane dependent on the
client code,but this is responsive enough so it can be used with either separate screen or part of
another screen such as pane
 */
@Composable
fun AdminOfficersScreen(
    token: String?,
    subOfficeId: String,
    onExitRequest:()->Unit,
    onEvent:(AdminEmployeeListEvent)->Unit,
) {
    val viewModel = viewModel {
        EmployeeListViewModel(
            controller = UiFactory.createEmployeeController(token = token)
        )
    }
    val employees=viewModel.controller.employees.collectAsState().value
    LaunchedEffect(Unit){
        viewModel.controller.fetch(subOfficeId)
    }

    SnackNProgressBarDecorator(
        isLoading = viewModel.isLoading.collectAsState(false).value,
        snackBarMessage = viewModel.screenMessage.collectAsState(null).value
    ){
        TopBarDecoratorCommon(
            topNavigationIcon = Icons.AutoMirrored.Default.ArrowBack,
            onNavigationIconClick = onExitRequest,
            topBarTitle = "Admin Officers"
        ) {

            ListOfAdminOfficer(
                modifier = Modifier.padding(it),
                officers = employees,
                onEvent = onEvent
            )

        }
    }


}


@Composable
private fun ListOfAdminOfficer(
    modifier: Modifier = Modifier,
    officers: List<AdminOfficerModel>,
    onEvent:(AdminEmployeeListEvent)->Unit,
) {
    if (officers.isNotEmpty()){
        Surface(
            shadowElevation = 8.dp
        ) {
            AdaptiveList(
                modifier = modifier,
                items = officers
            ) { employee ->
                AdminOfficerCard(
                    modifier = Modifier.padding(8.dp),
                    adminOfficer = employee,
                    onCallRequest = {
                        onEvent(AdminEmployeeListEvent.CallRequest(employee.phone))
                    },
                    onMessageRequest = {
                        onEvent(AdminEmployeeListEvent.MessageRequest(employee.phone))
                    },
                    onEmailRequest = {
                        onEvent(AdminEmployeeListEvent.EmailRequest(employee.email))
                    }
                )
            }

        }
    }
}

@Composable
private fun AdminOfficerCard(
    modifier: Modifier,
    adminOfficer: AdminOfficerModel,
    expandMode: Boolean = true,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {

    GenericEmployeeCard(
        modifier = modifier,
        name = adminOfficer.name,
        profileImageUrl = adminOfficer.profileImageLink,
        expandMode = expandMode,
        onCallRequest = onCallRequest,
        onEmailRequest = onEmailRequest,
        onMessageRequest = onMessageRequest,
        details = {
            EmployeeDetails(
                adminOfficer = adminOfficer,
                modifier = Modifier
            )
        })


}



@Composable
private fun EmployeeName(
    modifier: Modifier,
    name: String,
) {
    Text(
        modifier = modifier,
        text = name,
        style = CardTypography.title
    )

}

@Composable
private fun EmployeeDetails(
    modifier: Modifier,
    adminOfficer: AdminOfficerModel
) {
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = adminOfficer.achievements,
            style = CardTypography.subTitle
        )
        Text(
            text = adminOfficer.designations,
            style = CardTypography.title2
        )
        Text(
            text = adminOfficer.email,
            style = CardTypography.contactStyle
        )
        Text(
            text = adminOfficer.additionalEmail,
            style = CardTypography.contactStyle
        )
        Text(
            text = adminOfficer.phone,
            style = CardTypography.contactStyle
        )


    }
}

internal object  CardTypography{
    val title = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        fontFamily = FontFamily.Monospace
    )
    val subTitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        fontFamily = FontFamily.Monospace
    )
    val title2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
    )

    val contactStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        fontFamily = FontFamily.Monospace
    )


}