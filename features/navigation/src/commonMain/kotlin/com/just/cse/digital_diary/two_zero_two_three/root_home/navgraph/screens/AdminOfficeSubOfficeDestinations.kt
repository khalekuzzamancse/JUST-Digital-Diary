package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations.AdminOfficeSubOfficeEmployeeDestination

object AdminOfficeSubOfficeDestinations {
    val SUB_OFFICE_EMPLOYEES=Destination.createDestination("SUB_OFFICE_EMPLOYEES")
    @Composable
    fun AdminOfficeSubOfficeEmployees(
        subOfficeId: String,
        appEvent: AppEvent,
        onExitRequest: () -> Unit,
    ) {
        AdminOfficeSubOfficeEmployeeDestination(
            subOfficeId=subOfficeId,
            appEvent=appEvent,
            onExitRequest = onExitRequest
        )
    }
}