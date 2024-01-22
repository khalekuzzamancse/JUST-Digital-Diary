package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.FacultyModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.AuthDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.EventGalleryDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.FacultyListDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.NoteListDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.about_us.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.home.HomeDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.message_from_vc.MessageFromVCDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.search.SearchDestination
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen.NotesScreenViewModel
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point.NoteCreationScreen


object RootModuleDrawerDestinations {
    val HOME = createDestination("HOME")
    val FACULTY_LIST = createDestination("FACULTY_LIST")
    val ABOUT_US = createDestination("ABOUT_US")
    val MESSAGE_FROM_VC = createDestination("MESSAGE_FROM_VC")
    val NOTE_LIST = createDestination("NOTE_LIST")
    val EVENT_GALLERY = createDestination("EVENT_GALLERY")
    val SEARCH = createDestination("SEARCH")
    val NOTE_CREATION = createDestination("NOTE_CREATION")


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
        NoteListDestination(
            viewModel =NotesScreenViewModel(),
            onExitRequest = onExitRequest
        )
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
        EventGalleryDestination(onExitRequest = onExitRequest)

    }

    @Composable
    fun Search(
        onExitRequest: () -> Unit,
        onCallRequest: (String) -> Unit,
        onEmailRequest: (String) -> Unit,
        onMessageRequest: (String) -> Unit,
    ) {
        SearchDestination(
            onExitRequest = onExitRequest,
            onCallRequest = onCallRequest,
            onMessageRequest = onMessageRequest,
            onEmailRequest = onEmailRequest
        )
    }

    @Composable
    fun FacultyList(
        event: AppEvent,
        onExitRequest: () -> Unit,
    ) {
        FacultyListDestination(
            event = FacultyModuleEvent(
                onCallRequest = event.onCallRequest,
                onMessageRequest = event.onMessageRequest,
                onEmailRequest = event.onEmailRequest
            ),
            onExitRequest = onExitRequest
        )

    }

}

private fun createDestination(route: String) = object : Destination {
    override val route: String = route
}