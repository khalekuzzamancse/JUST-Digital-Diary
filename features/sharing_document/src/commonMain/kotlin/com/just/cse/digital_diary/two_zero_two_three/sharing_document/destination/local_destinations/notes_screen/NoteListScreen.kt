package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.note_details.NoteDetails
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.shared_notes.NoteListDestination

@Composable
fun NotesScreen() {
    val viewModel = remember { NotesScreenViewModel() }
    val openedNotes = viewModel.openedNote.collectAsState().value
    TwoPaneLayout(
        pane2AnimationState = openedNotes,
        showPane2 = openedNotes != null,
        props = TwoPaneProps(),
        pane1 = {
            NoteListDestination(
                modifier = Modifier,
                notes = viewModel.notes.collectAsState().value,
                onDetailsOpen = viewModel::onNoteDetailsRequested,
                onExitRequest = {},
            )
        },
        pane2 = {
            if (openedNotes != null) {
                NoteDetails(
                    note = openedNotes,
                    onExitRequest = viewModel::onCloseDetailsRequested
                )
            }
        }
    )


}