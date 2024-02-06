package com.just.cse.digital_diary.features.admin_office.destination.destinations

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.components.offices.AdminOffices
import com.just.cse.digitaldiary.twozerotwothree.core.di.admin_offices.AdminOfficeComponentProvider

@Composable
fun AdminOfficeScreen(
    onEmployeeListRequest: (String) -> Unit,
    onExitRequest: () -> Unit,
) {
    AdminOffices(
        onEmployeeListRequest =onEmployeeListRequest,
        onExitRequest =onExitRequest,
        officeRepository = AdminOfficeComponentProvider.getAdminOfficeRepository(),
        subOfficeRepository = AdminOfficeComponentProvider.getAdminSubOfficeRepository()
    )
}