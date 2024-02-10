package com.just.cse.digital_diary.features.admin_office.destination.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.components.offices_and_sub_offices.OfficesAndSubOffices
import com.just.cse.digitaldiary.twozerotwothree.core.di.admin_offices.AdminOfficeComponentProvider

@Composable
fun AdminOfficeAndSubOfficeRoute(
    onEmployeeListRequest: (String) -> Unit,
    onExitRequest: () -> Unit={},
    onBackButtonPress:@Composable (onBackButtonPress: () -> Unit)->Unit={},
) {
    OfficesAndSubOffices(
        onEmployeeListRequest =onEmployeeListRequest,
        officeRepository = AdminOfficeComponentProvider.getAdminOfficeRepository(),
        subOfficeRepository = AdminOfficeComponentProvider.getAdminSubOfficeRepository(),
        backHandler=onBackButtonPress
    )
}