package navigation.modal_drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import common.ui.navigation.modal_drawer.drawer_decorator.ModalDrawerDecorator
import common.ui.navigation.modal_drawer.drawer_decorator.ModalDrawerStateManger
import common.ui.navigation.modal_drawer.sheet.event.DrawerSheetEvent


object TopmostNavigationHost {
    private val destinations = TopRoutes.navGroups
    private val drawerManager = ModalDrawerStateManger(
        group = destinations,
        itemVisibilityDelay = 5// ms
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
                DrawerHeader(
                    onLogOutRequest = onLogOutRequest
                )
            },
            content = navHost
        )

    }


}