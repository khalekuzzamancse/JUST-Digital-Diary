package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.NavigationEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.AdminOfficeSubOfficeDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.AuthDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.DepartmentInfoModuleDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.Destination
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.ModalDrawerHandler
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.TopMostDestinations

private val drawerHandler = ModalDrawerHandler()

@Composable
fun AndroidRootNavigation(
    handler: ModalDrawerHandler = drawerHandler,
    appEvent: AppEvent,
) {
    val navHostController = rememberNavController()
    val navigator = remember {
        Navigator(navHostController)
    }
    val navigateTo: (Destination) -> Unit = navigator::navigateWithReplacementLastDestination
    var notSignedIn by remember { mutableStateOf(true) }
    val onLoginSuccess:()->Unit={
        notSignedIn = false
    }
    val onLogOutRequest:() -> Unit ={
        notSignedIn=true
    }
    if (notSignedIn)
        AuthDestinations.Auth(
            onLoginSuccess = onLoginSuccess, onExitRequest = {}
        )
    else {
        RootModuleDrawer(
            onLogOutRequest = onLogOutRequest,
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
                        navigateTo(TopMostDestinations.NOTE_CREATION)
                    },
                    onDepartmentInfoRequest = {
                        //navigate by pushing back stack so that on back press previous destination will be back
                        navigator.navigatePushing(DepartmentInfoModuleDestinations.DEPARTMENT_INFO)
                    },
                    onAdminOfficeSubOfficeRequest = {
                        //navigate by pushing back stack so that on back press previous destination will be back
                        navigator.navigatePushing(AdminOfficeSubOfficeDestinations.SUB_OFFICE_EMPLOYEES)
                    }
                )
            }
        )

    }


}