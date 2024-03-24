package administration.navgraph.screens

import administration.di.AdminOfficeComponentProvider
import administration.navgraph.event.AdminEvent
import administration.ui.AdminFeatureEvent
import administration.ui.officers.route.AdminOfficers
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.TopBarDecorator

@Composable
fun AdminOfficersScreen(
    subOfficeId: String,
    onExitRequest:()->Unit,
    onEvent:(AdminEvent)->Unit,
) {
    TopBarDecorator(
        topNavigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        onNavigationIconClick = onExitRequest,
        topBarTitle = "Admin Officers"
    ) {
        AdminOfficers(
            modifier = Modifier.padding(it),
            subOfficeId=subOfficeId,
            repository = AdminOfficeComponentProvider.getAdminOfficersListRepository(),
            onEvent={event->
                convertEvent(event)?.let(onEvent)

            }
        )
    }

}
private fun convertEvent(event: AdminFeatureEvent): AdminEvent?{
    val ev: AdminEvent? = when (event) {
        is AdminFeatureEvent.CallRequest -> AdminEvent.CallRequest(event.number)
        is AdminFeatureEvent.MessageRequest -> AdminEvent.MessageRequest(
            event.number
        )
        is AdminFeatureEvent.EmailRequest -> AdminEvent.EmailRequest(event.email)
        else -> {
            null
        }
    }
    return ev

}