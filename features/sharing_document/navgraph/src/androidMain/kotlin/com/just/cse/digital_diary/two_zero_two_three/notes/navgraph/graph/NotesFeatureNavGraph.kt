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
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.routes.ListAndDetails
import com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.routes.NoteListTopBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.create_note.CreateNoteDestination
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.details.NoteDetails
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.note_list.ListOfNotes


object NotesFeatureNavGraph {
    const val ROUTE = "NotesFeatureNavGraph"
    private const val NOTE_CREATION_SCREEN = "NoteCreation"
    private const val NOTE_LIST_SCREEN = "NoteListScreen"

    @Composable
    fun Graph(navController: NavHostController = rememberNavController()) {
        var selectedNoteId: String? by remember { mutableStateOf(null) }
        NavHost(
            navController = navController,
            startDestination = NOTE_LIST_SCREEN
        ) {
            composable(NOTE_LIST_SCREEN) {
                NoteListTopBarDecorator(
                    onNoteCreateRequest = {
                       navController.navigate(NOTE_CREATION_SCREEN)
                    }
                ) {
                    WindowSizeDecorator(
                        modifier = Modifier.padding(it),
                        onCompact = {
                            selectedNoteId.let { id ->
                                if (id != null) {
                                    NoteDetails(
                                        modifier = Modifier, id = id
                                    )
                                    BackHandler(//overriding the back button functionality
                                        onBack = {
                                            selectedNoteId=null
                                        }
                                    )
                                } else {
                                    ListOfNotes(
                                        modifier = Modifier.padding(it),
                                        onDetailsRequest = {
                                            selectedNoteId=it
                                        }
                                    )
                                }
                            }

                        },
                        onNonCompact = {
                            ListAndDetails(
                                selectedNoteId = selectedNoteId,
                                onDetailsRequest = { id ->
                                    selectedNoteId = id
                                })
                            BackHandler(//overriding the back button functionality
                                onBack = {
                                    selectedNoteId=null
                                }
                            )
                        }
                    )

                }
            }
            composable(NOTE_CREATION_SCREEN) {
                CreateNoteDestination(Modifier)
            }
        }


    }
}