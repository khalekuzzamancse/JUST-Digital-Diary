package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.event.FacultyModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.about_us.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.message_from_vc.MessageFromVCDestination
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.OtherInfoRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations.admin_office.AdminOfficeDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.faculites.FacultyListDestination


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
        onOpenDrawerRequest: () -> Unit,
        onLogOutRequest: () -> Unit,
    ) {
//        HomeDestination(
//            onCreateNoteRequest = onCreateNoteRequest,
//            onOpenDrawerRequest = onOpenDrawerRequest,
//            onLogOutRequest = onLogOutRequest,
//        )

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
       AboutUsDestination(
           repository = OtherInfoRepositoryImpl(),
           onExitRequest = onExitRequest
       )

    }

    @Composable
    fun MessageFromVC(onExitRequest: () -> Unit) {
       MessageFromVCDestination(
           repository = OtherInfoRepositoryImpl(),
           onExitRequest = onExitRequest
       )
    }

    @Composable
    fun EventGallery(
        onExitRequest: () -> Unit
    ) {
        com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations.EventGallery(
            onExitRequest = onExitRequest
        )

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
            event = FacultyModuleEvent(
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
            onEmployeeListRequest=onEmployeeListRequest
        )

    }

}

