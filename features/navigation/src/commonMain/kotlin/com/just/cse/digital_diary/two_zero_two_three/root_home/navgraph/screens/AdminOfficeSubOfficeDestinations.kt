package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.admin_officers.AdminOfficers
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent

object AdminOfficeSubOfficeDestinations {
    val SUB_OFFICE_EMPLOYEES=Destination.createDestination("SUB_OFFICE_EMPLOYEES")
    @Composable
    fun AdminOfficeSubOfficeEmployees(
        subOfficeId: String,
        appEvent: AppEvent,
        onExitRequest: () -> Unit,
    ) {
        AdminOfficers()
    }

}