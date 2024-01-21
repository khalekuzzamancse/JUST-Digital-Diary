package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.AuthDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.EventGalleryDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.NoteListDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.about_us.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.home.HomeDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.message_from_vc.MessageFromVCDestination
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point.NoteCreationScreen

object TopMostDestinations {
    @Composable
    fun Home(
        onCreateNoteRequest: () -> Unit,
        onOpenDrawerRequest: () -> Unit,
        onLogOutRequest: () -> Unit,
    ) {
        HomeDestination(
            onCreateNoteRequest = onCreateNoteRequest,
            onOpenDrawerRequest = onOpenDrawerRequest,
            onLogOutRequest = onLogOutRequest,
        )

    }

    @Composable
    fun Auth(
        onLoginSuccess: () -> Unit,
        onExitRequest: () -> Unit
    ) {
        AuthDestination(
            onLoginSuccess = onLoginSuccess,
            onExitRequest = onExitRequest
        )

    }

    @Composable
    fun CreateNote(
        onExitRequest: () -> Unit
    ) {
        NoteCreationScreen(onExitRequest = onExitRequest)
    }

    @Composable
    fun NoteList(
        onExitRequest: () -> Unit
    ) {
       // NoteListDestination(onExitRequest = onExitRequest)
    }


    @Composable
    fun AboutUs(
        onExitRequest: () -> Unit
    ) {
        AboutUsDestination(onExitRequest = onExitRequest)

    }

    @Composable
    fun MessageFromVC(onExitRequest: () -> Unit) {
        MessageFromVCDestination(onExitRequest = onExitRequest)
    }

    @Composable
    fun EventGallery(
        onExitRequest: () -> Unit
    ) {
        EventGalleryDestination(onExitRequest=onExitRequest)

    }

    @Composable
    fun ExploreJust() {

    }
}