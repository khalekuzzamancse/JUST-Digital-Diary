package com.just.cse.digital_diary.features.admin_office.destination.nav_graph.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.admin_office.destination.destinations.AdminOfficeScreen

internal class OfficeListScreen(
    private val onExitRequest: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        AdminOfficeScreen(
            onExitRequest = onExitRequest,
            onEmployeeListRequest = { subOfficeId ->
                navigator?.push(
                    AdminOfficersListScreen(
                        onExitRequest = {
                            navigator.pop()
                        },
                        subOfficeId = subOfficeId
                    )
                )

            }
        )
    }
}