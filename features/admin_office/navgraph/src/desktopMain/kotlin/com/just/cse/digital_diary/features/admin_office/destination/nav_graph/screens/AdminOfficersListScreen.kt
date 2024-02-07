package com.just.cse.digital_diary.features.admin_office.destination.nav_graph.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.features.admin_office.destination.screens.AdminOfficersScreen

internal class AdminOfficersListScreen(
    private val subOfficeId: String,
    private val onExitRequest: () -> Unit
): Screen {
    @Composable
    override fun Content() {
        AdminOfficersScreen(
            subOfficeId = subOfficeId,
            onExitRequest =onExitRequest
        )
    }
}