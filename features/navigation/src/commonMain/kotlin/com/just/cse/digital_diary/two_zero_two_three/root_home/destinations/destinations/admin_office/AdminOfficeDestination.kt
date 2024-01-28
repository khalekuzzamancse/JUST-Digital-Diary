package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations.admin_office

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.offices.AdminOffices


@Composable
internal fun AdminOfficeDestination(
    onExitRequest:()->Unit,
    onEmployeeListRequest:(String)->Unit,
) {
    AdminOffices(
        onExitRequest =onExitRequest,
        onEmployeeListRequest = onEmployeeListRequest
    )

}