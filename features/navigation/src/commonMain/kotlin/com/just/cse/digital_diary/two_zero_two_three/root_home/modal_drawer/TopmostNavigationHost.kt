package com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.drawer_decorator.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.drawer_decorator.ModalDrawerStateManger
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.NavItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.event.DrawerSheetEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state.NavGroup


object TopmostNavigationHost {
    private val destinations = TopRoutes.navGroups
    private val drawerManager = ModalDrawerStateManger(
        group = destinations,
    )

    fun openDrawer() {
        drawerManager.openDrawer()
    }

    fun selectFirst() {
        destinations.firstOrNull()?.let { group ->
            group.items.firstOrNull()?.let { onItemSelected(it.label) }
        }

    }

    private fun onItemSelected(id: String) {
        drawerManager.makeSelection(id)
    }


    @Composable
    fun DrawerHost(
        onRouteSelected: (TopRoutes.NavDestination) -> Unit,
        onLogOutRequest: () -> Unit = {},
        navHost: @Composable () -> Unit,
    ) {
        ModalDrawerDecorator(
            state = drawerManager.drawerState.collectAsState().value,
            onEvent = { event ->
                if (event is DrawerSheetEvent.Selected) {
                    onItemSelected(event.id)
                    TopRoutes.getDestination(event.id)?.let(onRouteSelected)
                }
            },
            header = {
                Header(
                    onLogOutRequest = onLogOutRequest
                )
            },
            content = navHost
        )

    }


}