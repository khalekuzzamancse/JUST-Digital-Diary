package com.just.cse.digital_diary.features.admin_office.destination.nav_graph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.admin_office.destination.nav_graph.screens.OfficeListScreen


@Composable
fun AdminOfficeNavGraph(onExitRequest: () -> Unit) {
    Navigator(
        OfficeListScreen(
            onExitRequest = onExitRequest
        )
    )
}