package com.just.cse.digital_diary.features.admin_office.destination.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.components.AdminFeatureEvent
import com.just.cse.digital_diary.features.admin_office.components.officers.AdminOfficers
import com.just.cse.digital_diary.features.admin_office.destination.event.AdminEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.event.AdminEmployeeListEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.TopBarDecorator
import com.just.cse.digitaldiary.twozerotwothree.core.di.admin_offices.AdminOfficeComponentProvider

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