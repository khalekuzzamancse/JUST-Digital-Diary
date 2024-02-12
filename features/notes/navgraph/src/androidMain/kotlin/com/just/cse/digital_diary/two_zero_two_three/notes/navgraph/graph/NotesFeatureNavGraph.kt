package com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.graph

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.routes.NoteListTopBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.list_and_details.NotesAndDetailsRoute
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.create_note.CreateNoteDestination


object NotesFeatureNavGraph {
    private const val NOTE_CREATION_SCREEN = "NoteCreation"
    private const val NOTE_AND_DETAILS_ROUTE = "NoteListScreen"

    @Composable
    fun Graph(
        navController: NavHostController = rememberNavController(),
        onBackPressed:()->Unit,
        onExitRequest:()->Unit,
    ) {
        var selectedNoteId: String? by remember { mutableStateOf(null) }
        NavHost(
            navController = navController,
            startDestination = NOTE_AND_DETAILS_ROUTE
        ) {
            composable(NOTE_AND_DETAILS_ROUTE) {
                NoteListTopBarDecorator(
                    onNoteCreateRequest = {
                        navController.navigate(NOTE_CREATION_SCREEN)
                    }
                ) { padding ->
                    NotesAndDetailsRoute(
                        modifier = Modifier.padding(padding),
                        onDetailsRequest = { id ->
                            selectedNoteId = id
                        },
                        onNoteDetailsCloseRequest = {
                            selectedNoteId = null
                        },
                        selectedNoteId = selectedNoteId,
                        backButtonHandler = { callback ->
                            BackHandler(onBack = {
                                val isConsumed=callback()
                                if (!isConsumed){
                                    onBackPressed()
                                }
                            })
                        },
                        onExitRequest = onExitRequest
                    )


                }
            }
            composable(NOTE_CREATION_SCREEN) {
                CreateNoteDestination(Modifier)
            }
        }


    }
}