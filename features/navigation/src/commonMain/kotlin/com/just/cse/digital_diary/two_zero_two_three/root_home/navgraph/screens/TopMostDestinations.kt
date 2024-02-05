package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.just.cse.digital_diary.features.faculty.destination.FacultyDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.EventGalleryDestination
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.MessageFromVCDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.TopBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations.admin_office.AdminOfficeDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.faculites.FacultyListDestination
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider


object TopMostDestinations {
    val HOME = Destination.createDestination("HOME")
    val FACULTY_LIST = Destination.createDestination("FACULTY_LIST")
    val ADMIN_OFFICES = Destination.createDestination("ADMIN_OFFICES")
    val ABOUT_US = Destination.createDestination("ABOUT_US")
    val MESSAGE_FROM_VC = Destination.createDestination("MESSAGE_FROM_VC")
    val NOTE_LIST = Destination.createDestination("NOTE_LIST")
    val EVENT_GALLERY = Destination.createDestination("EVENT_GALLERY")
    val SEARCH = Destination.createDestination("SEARCH")
    val NOTE_CREATION = Destination.createDestination("NOTE_CREATION")

    @Composable
    fun Home(
        onCreateNoteRequest: () -> Unit,
    ) {
        LaunchedEffect(Unit) {
            AuthComponentProvider.updateAuthToken()
        }
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {}
                ) {
                    Icon(Icons.Default.Add, null)
                }
            },
            floatingActionButtonPosition = FabPosition.Center,

            ) {

        }


    }


    @Composable
    fun CreateNote(
        onExitRequest: () -> Unit
    ) {
        //NoteCreationScreen(onExitRequest = onExitRequest)
    }

    @Composable
    fun NoteList(
        onExitRequest: () -> Unit
    ) {
//        NoteListDestination(
//            viewModel = NotesScreenViewModel(),
//            onExitRequest = onExitRequest
//        )
    }


    @Composable
    fun AboutUs(
        onExitRequest: () -> Unit
    ) {
        AboutUsDestination()

    }

    @Composable
    fun MessageFromVC(onExitRequest: () -> Unit) {
        MessageFromVCDestination()
    }

    @Composable
    fun EventGallery(
        onExitRequest: () -> Unit
    ) {

        EventGalleryDestination()


    }

    @Composable
    fun Search(
        onExitRequest: () -> Unit,
        onCallRequest: (String) -> Unit,
        onEmailRequest: (String) -> Unit,
        onMessageRequest: (String) -> Unit,
    ) {
//        SearchDestination(
//            onExitRequest = onExitRequest,
//            onCallRequest = onCallRequest,
//            onMessageRequest = onMessageRequest,
//            onEmailRequest = onEmailRequest
//        )
    }

    @Composable
    fun FacultyList(
        onDepartmentInfoRequest: (String) -> Unit,
        onExitRequest: () -> Unit,
    ) {

        FacultyListDestination(
            event = FacultyDestinationEvent(
                onDepartmentInfoRequest = onDepartmentInfoRequest
            ),
            onExitRequest = onExitRequest,
        )

    }

    @Composable
    fun AdminOfficeList(
        onEmployeeListRequest: (subOfficeId: String) -> Unit,
        onExitRequest: () -> Unit,
    ) {
        AdminOfficeDestination(
            onExitRequest = onExitRequest,
            onEmployeeListRequest = onEmployeeListRequest
        )

    }

}

