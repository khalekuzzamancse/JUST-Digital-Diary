package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph.OtherFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.TopMostDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.ModalDrawerHandler

private val drawerHandler = ModalDrawerHandler()

@Composable
fun AndroidRootNavigation(
    handler: ModalDrawerHandler = drawerHandler,
    appEvent: AppEvent,
) {
    val navHostController = rememberNavController()


    var notSignedIn by remember { mutableStateOf(false) }
    val onLoginSuccess:(String,String)->Unit={_, _ ->
        notSignedIn = false
    }
    val onLogOutRequest:() -> Unit ={
        notSignedIn=true
    }
//    if (notSignedIn)
//        AuthDestinations.Auth(
//            onLoginSuccess =onLoginSuccess, onExitRequest = {}
//        )
//    else {
        RootModuleDrawer(
            onLogOutRequest = onLogOutRequest,
            drawerHandler = handler,
            onEvent = {destination->
                     when(destination){
                         TopMostDestination.Home->{
                             OtherFeatureNavGraph.navigateToHome(navHostController)
                         }
                         TopMostDestination.MessageFromVC->{
                             OtherFeatureNavGraph.navigateToMessageFromVC(navHostController)
                         }
                         TopMostDestination.AboutUs->{
                             OtherFeatureNavGraph.navigateToAboutUs(navHostController)
                         }
                         TopMostDestination.EventGallery->{
                             OtherFeatureNavGraph.navigateToEventGallery(navHostController)
                         }
                         TopMostDestination.FacultyList->{
                             navHostController.navigate(GraphRoutes.FACULTY_FEATURE)
                         }
                         TopMostDestination.AdminOffice->{
                             navHostController.navigate(GraphRoutes.ADMIN_OFFICE_FEATURE)
                         }
                         TopMostDestination.NoteList->{
                             navHostController.navigate(GraphRoutes.NOTES_FEATURE)
                         }
                         else -> {}
                     }
            },
            navHost = {
                RootNavGraph(
                    appEvent = appEvent,
                    openDrawerRequest = handler::openDrawer,
                    navController = navHostController
                )
            }
        )

   // }


}