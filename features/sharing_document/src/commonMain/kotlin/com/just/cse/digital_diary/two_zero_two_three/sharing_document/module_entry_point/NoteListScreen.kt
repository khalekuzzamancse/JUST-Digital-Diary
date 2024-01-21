package com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.note_details.NoteDetails
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen.NoteListDestination
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen.NotesScreenViewModel

@Composable
fun NotesScreen(
    onExitRequest:()->Unit,
) {
    val viewModel = remember { NotesScreenViewModel() }
    val openedNotes = viewModel.openedNote.collectAsState().value
    TwoPaneLayout(
        secondaryPaneAnimationState = openedNotes,
        showTopOrRightPane = openedNotes != null,
        props = TwoPaneProps(),
        leftPane = {
            NoteListDestination(
                modifier = Modifier,
                notes = viewModel.notes.collectAsState().value,
                onDetailsOpen = viewModel::onNoteDetailsRequested,
                onExitRequest = onExitRequest,
            )
        },
        topOrRightPane = {
            if (openedNotes != null) {
                NoteDetails(
                    note = openedNotes,
                    onExitRequest = viewModel::onCloseDetailsRequested
                )
            }
        }
    )


}