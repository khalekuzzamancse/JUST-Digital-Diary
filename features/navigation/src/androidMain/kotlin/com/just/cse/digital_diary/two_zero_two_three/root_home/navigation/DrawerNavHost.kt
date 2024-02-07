package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.just.cse.digital_diary.features.admin_office.destination.AdminOfficeFeatureNavGraph
import com.just.cse.digital_diary.features.faculty.destination.FacultyFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.TopMostDestinations

@Composable
fun DrawerNavHost(
    modifier: Modifier = Modifier,
    appEvent: AppEvent,
    openDrawerRequest: () -> Unit,
    onNoteCreationRequest: () -> Unit,
    navController: NavHostController,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = TopMostDestinations.HOME.route
    ) {
        composable(route = TopMostDestinations.HOME.route) {
            TopMostDestinations.Home(
                onCreateNoteRequest = onNoteCreationRequest,
            )

        }
        FacultyFeatureNavGraph.graph(this, navController)
        AdminOfficeFeatureNavGraph.graph(this, navController)

        composable(route = TopMostDestinations.SEARCH.route) {
            TopMostDestinations.Search(
                onExitRequest = openDrawerRequest,
                onCallRequest = appEvent.onCallRequest,
                onEmailRequest = appEvent.onEmailRequest,
                onMessageRequest = appEvent.onMessageRequest
            )
        }
        composable(route = TopMostDestinations.EVENT_GALLERY.route) {
            TopMostDestinations.EventGallery(
                onExitRequest = openDrawerRequest
            )
        }
        composable(route = TopMostDestinations.MESSAGE_FROM_VC.route) {
            TopMostDestinations.MessageFromVC(
                onExitRequest = openDrawerRequest
            )
        }

        composable(route = TopMostDestinations.NOTE_LIST.route) {
            TopMostDestinations.NoteList(
                onExitRequest = openDrawerRequest,
            )
        }
        composable(route = TopMostDestinations.ABOUT_US.route) {
            TopMostDestinations.AboutUs(
                onExitRequest = openDrawerRequest
            )
        }
        composable(route = TopMostDestinations.NOTE_CREATION.route) {
            TopMostDestinations.CreateNote(
                onExitRequest = openDrawerRequest
            )
        }


    }

}
