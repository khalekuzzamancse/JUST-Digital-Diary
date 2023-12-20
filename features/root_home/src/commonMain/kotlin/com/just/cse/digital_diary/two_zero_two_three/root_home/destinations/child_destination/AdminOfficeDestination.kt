package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.offices.AdminOfficeScreen

@Composable
internal fun AdminOfficeDestination(
    onSubOfficeSelected:(String) -> Unit,
    onExitRequest:()->Unit,
) {
    AdminOfficeScreen(
        onSubOfficeSelected = onSubOfficeSelected,
        onExitRequest =onExitRequest
    )

}