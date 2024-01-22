package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.features.faculty.faculty.FacultyModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.RootDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen.NotesScreenViewModel

private val viewModel = ModalDrawerHandler()

@Composable
fun RootDestinationScreen(
    appEvent: AppEvent
) {

//    val openDrawer: () -> Unit = {
//        viewModel.openDrawer()
//    }
//
//    RootModuleDrawer(
//        drawerHandler = viewModel
//    ) { localDestination ->
//        when (localDestination) {
//            RootDestinations.HOME -> {
//                RootModuleDrawerDestinations.Home(
//                    onOpenDrawerRequest = openDrawer,
//                    onCreateNoteRequest = {
//                        //  navigator?.push(CreateNoteScreen())
//                    },
//                    onLogOutRequest = {}
//                )
//
//
//            }
//
//            RootDestinations.MESSAGE_FROM_VC -> {
//                RootModuleDrawerDestinations.MessageFromVC(
//                    onExitRequest = openDrawer
//                )
//
//            }
//
//            RootDestinations.ABOUT_US -> {
//                RootModuleDrawerDestinations.AboutUs(
//                    onExitRequest = openDrawer
//                )
//
//
//            }
//
//            RootDestinations.Search -> {
//                RootModuleDrawerDestinations.Search(
//                    onExitRequest = {
//                        viewModel.onSectionSelected(0)//go to home page
//
//                    },
//                    onCallRequest = appEvent.onCallRequest,
//                    onMessageRequest = appEvent.onMessageRequest,
//                    onEmailRequest = appEvent.onEmailRequest
//                )
//
//            }
//
//            RootDestinations.EventGallery -> {
//                RootModuleDrawerDestinations.EventGallery(
//                    onExitRequest = openDrawer
//                )
//
//            }
//
//            RootDestinations.EXPLORE_JUST -> {
//                appEvent.onWebsiteViewRequest("https://just.edu.bd/")
//                viewModel.onSectionSelected(0)//go to home screen
//
//            }
//
//            RootDestinations.NOTE_LIST -> {
//                val notesScreenViewModel = remember { NotesScreenViewModel() }
//                RootModuleDrawerDestinations.NoteList(
//                    onExitRequest = openDrawer,
//
//                )
//
//            }
//
//            RootDestinations.FACULTY_LIST -> {
////                RootModuleDrawerDestinations.FacultyList(
////                    onExitRequest = openDrawer,
////                    event = FacultyModuleEvent(
////                        onEmailRequest = appEvent.onEmailRequest,
////                        onMessageRequest = appEvent.onMessageRequest,
////                        onCallRequest = appEvent.onCallRequest
////                    )
////                )
//
//            }
//        }
//    }


}
