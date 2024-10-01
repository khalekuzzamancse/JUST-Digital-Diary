package administration.ui.public_

import administration.controller_presenter.model.AdminOfficerModel
import administration.controller_presenter.factory.UiFactory
import administration.ui.common.SnackNProgressBarDecorator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import common.ui.TopBarDecoratorCommon
import common.ui.AdaptiveList


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
                modifier = Modifier,
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
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp
    ) {

        Column(
            modifier = modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
//            ImageLoader(
//                url = adminOfficer.profileImageLink,
//                modifier = Modifier
//                    .heightIn(max = 100.dp)
//                    .widthIn(max = 150.dp)
//                    .background(Color.Red)
//                    .align(Alignment.CenterHorizontally),
//            )
            Box(
                modifier = Modifier
                    .height( 100.dp)
                    .width(150.dp)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .align(Alignment.CenterHorizontally),
            )
            if (expandMode) {
                ExpandAbleInfo(
                    adminOfficer = adminOfficer
                )
            } else {
                EmployeeName(
                    name = adminOfficer.name,
                    modifier = Modifier
                )
                EmployeeDetails(
                    adminOfficer = adminOfficer,
                    modifier = Modifier
                )
            }
            Spacer(Modifier.height(8.dp))
            Controls(
                onCallRequest=onCallRequest,
                onMessageRequest = onMessageRequest,
                onEmailRequest = onEmailRequest
            )


        }


    }

}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ExpandAbleInfo(
    modifier: Modifier = Modifier,
    adminOfficer: AdminOfficerModel
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        FlowRow(
            modifier = Modifier,
//            verticalAlignment = Alignment.CenterVertically
        ) {
            //what if the name doesn't fit into one Row?,then button will be hidden
            EmployeeName(
                name = adminOfficer.name,
                modifier = Modifier
            )
            Icon(
                imageVector = if (expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                contentDescription = null,
                modifier = Modifier.clickable {
                    expanded = !expanded
                }
            )
        }
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
            exit= shrinkOut() + fadeOut()
        ) {
            EmployeeDetails(
                modifier = Modifier,
                adminOfficer = adminOfficer
            )
        }

    }

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
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Controls(
    modifier: Modifier = Modifier,
    onCallRequest: () -> Unit,
    onEmailRequest: () -> Unit,
    onMessageRequest: () -> Unit,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        IconButton(
            onClick = onCallRequest
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        IconButton(
            onClick = onEmailRequest,
        ) {
            Icon(
                Icons.Default.Email,
                null,
                tint = MaterialTheme.colorScheme.primary
            )

        }
        IconButton(
            onClick = onMessageRequest
        ) {
            Icon(
                Icons.AutoMirrored.Filled.Message,
                null,
                tint = MaterialTheme.colorScheme.primary
            )

        }
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