package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.NavigationEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph.Destination
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph.ModalDrawerHandler
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph.RootModuleDrawerDestinations

private val drawerHandler = ModalDrawerHandler()

@Composable
fun ScreenWithDrawer(
    handler: ModalDrawerHandler = drawerHandler,
    appEvent: AppEvent,
) {
    val navHostController = rememberNavController()
    val navigator = remember {
        Navigator(navHostController)
    }
    val navigateTo: (Destination) -> Unit = navigator::navigateToDrawerDestination

    RootModuleDrawer(
        drawerHandler = handler,
        navigationEvent = NavigationEvent(
            onWebsiteViewRequest = appEvent.onWebsiteViewRequest,
            drawerDestinationNavigationRequest = navigateTo
        ),
        navHost = {
            DrawerNavHost(
                appEvent = appEvent,
                openDrawerRequest = handler::openDrawer,
                navController = navHostController,
                onNoteCreationRequest = {
                    navigateTo(RootModuleDrawerDestinations.NOTE_CREATION)
                }
            )
        }
    )

}