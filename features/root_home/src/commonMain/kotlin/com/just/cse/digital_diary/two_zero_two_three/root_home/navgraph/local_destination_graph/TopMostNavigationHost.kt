package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.RootDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.DrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.EventGalleryDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.NoteListDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.about_us.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.home.HomeDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.message_from_vc.MessageFromVCDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.search.SearchDestination
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen.NotesScreenViewModel

private val viewModel = ModalDrawerHandler()

@Composable
fun RootDestinationScreen(
    appEvent: AppEvent
) {

    val openDrawer: () -> Unit = {
        viewModel.openDrawer()
    }

    DrawerDecorator(
        drawerHandler = viewModel
    ) { localDestination ->
        when (localDestination) {
            RootDestinations.HOME -> {
                    HomeDestination(
                        onOpenDrawerRequest = openDrawer,
                        onCreateNoteRequest = {
                            //  navigator?.push(CreateNoteScreen())
                        }
                    )


            }

            RootDestinations.MESSAGE_FROM_VC -> {
                    MessageFromVCDestination(
                        onExitRequest = openDrawer
                    )

            }

            RootDestinations.ABOUT_US -> {
                    AboutUsDestination(
                        onExitRequest = openDrawer
                    )



            }

            RootDestinations.Search -> {
                viewModel.hideDefaultTopBar()
                    SearchDestination(
                        onExitRequest = {
                            viewModel.showDefaultTopBar()
                            viewModel.onSectionSelected(0)//go to home page

                        },
                        onCallRequest = appEvent.onCallRequest,
                        onMessageRequest = appEvent.onMessageRequest,
                        onEmailRequest = appEvent.onEmailRequest
                    )

            }

            RootDestinations.EventGallery -> {
                    EventGalleryDestination(
                        onExitRequest = openDrawer
                    )

            }

            RootDestinations.EXPLORE_JUST -> {
                appEvent.onWebsiteViewRequest("https://just.edu.bd/")
                viewModel.onSectionSelected(0)//go to home screen

            }

            RootDestinations.NOTE_LIST -> {
                val notesScreenViewModel = remember { NotesScreenViewModel() }
                    NoteListDestination(
                        onExitRequest = openDrawer,
                        viewModel = notesScreenViewModel
                    )

            }
        }
    }


}
//when (currentDestinationIndex) {
//    RootDestinations.FACULTY_MEMBERS -> {
//        navigatorManager.navigateToFacultyModule(
//            event= FacultyModuleEvent(
//                onEmailRequest =appEvent.onEmailRequest,
//                onMessageRequest = appEvent.onMessageRequest,
//                onCallRequest = appEvent.onCallRequest
//            )
//        )
//    }
//
//
//
//}