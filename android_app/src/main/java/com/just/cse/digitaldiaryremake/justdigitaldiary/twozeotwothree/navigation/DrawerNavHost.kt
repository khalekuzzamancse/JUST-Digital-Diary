package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph.RootModuleDrawerDestinations

@Composable
fun DrawerNavHost(
    modifier: Modifier = Modifier,
    appEvent: AppEvent,
    openDrawerRequest:()->Unit,
    onNoteCreationRequest:()->Unit,
    navController: NavHostController,
) {
    NavHost(
        modifier=modifier,
        navController = navController,
        startDestination = RootModuleDrawerDestinations.HOME.route
    ) {

        composable(route = RootModuleDrawerDestinations.HOME.route) {
            RootModuleDrawerDestinations.Home(
                onCreateNoteRequest = onNoteCreationRequest,
                onOpenDrawerRequest = openDrawerRequest) {
            }

        }

        composable(route = RootModuleDrawerDestinations.FACULTY_LIST.route) {
            RootModuleDrawerDestinations.FacultyList(
                onExitRequest = openDrawerRequest,
                event =appEvent
            )
        }
        composable(route = RootModuleDrawerDestinations.SEARCH.route) {
            RootModuleDrawerDestinations.Search(
                onExitRequest = openDrawerRequest,
                onCallRequest = appEvent.onCallRequest,
                onEmailRequest = appEvent.onEmailRequest,
                onMessageRequest = appEvent.onMessageRequest
            )
        }
        composable(route = RootModuleDrawerDestinations.EVENT_GALLERY.route) {
            RootModuleDrawerDestinations.EventGallery(
                onExitRequest =openDrawerRequest
            )


        }
        composable(route = RootModuleDrawerDestinations.MESSAGE_FROM_VC.route) {
            RootModuleDrawerDestinations.MessageFromVC(
                onExitRequest =openDrawerRequest
            )

        }

        composable(route = RootModuleDrawerDestinations.NOTE_LIST.route) {
            RootModuleDrawerDestinations.NoteList(
                onExitRequest = openDrawerRequest,
            )
        }
        composable(route = RootModuleDrawerDestinations.ABOUT_US.route) {
            RootModuleDrawerDestinations.AboutUs(
                onExitRequest =openDrawerRequest
            )
        }
        composable(route = RootModuleDrawerDestinations.NOTE_CREATION.route) {
            RootModuleDrawerDestinations.CreateNote(
                onExitRequest =openDrawerRequest
            )
        }

    }

}