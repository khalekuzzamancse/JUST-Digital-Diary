package com.just.cse.digital_diary.features.admin_office.destination.destinations

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.components.AdminOfficers
import com.just.cse.digitaldiary.twozerotwothree.core.di.admin_offices.AdminOfficeComponentProvider

@Composable
fun AdminOfficerList(
    subOfficeId: String,
    onExitRequest: () -> Unit
) {
    AdminOfficers(
        onExitRequest=onExitRequest,
        subOfficeId=subOfficeId,
        repository = AdminOfficeComponentProvider.getAdminOfficersListRepository()
    )
}