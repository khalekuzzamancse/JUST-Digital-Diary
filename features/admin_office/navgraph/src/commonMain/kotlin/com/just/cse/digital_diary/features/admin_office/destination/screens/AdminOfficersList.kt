package com.just.cse.digital_diary.features.admin_office.destination.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.components.officers.AdminOfficers
import com.just.cse.digital_diary.two_zero_two_three.common_ui.TopBarDecorator
import com.just.cse.digitaldiary.twozerotwothree.core.di.admin_offices.AdminOfficeComponentProvider

@Composable
fun AdminOfficersScreen(
    subOfficeId: String,
    onExitRequest:()->Unit,
) {
    TopBarDecorator(
        topNavigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        onNavigationIconClick = onExitRequest,
        topBarTitle = "Admin Officers"
    ) {
        AdminOfficers(
            subOfficeId=subOfficeId,
            repository = AdminOfficeComponentProvider.getAdminOfficersListRepository()
        )
    }

}