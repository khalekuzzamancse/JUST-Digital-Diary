package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.a.notes_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps
import com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.note_list.NoteList
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.a.details.NoteDetails
import com.just.cse.digitaldiary.twozerotwothree.core.di.notes.NotesComponentProvider


@Composable
fun NotesListDestination() {
    val viewModel = remember { NotesListViewModel(NotesComponentProvider.getNotesRepository()) }
    val openedNotes = viewModel.openedNote.collectAsState().value
    LaunchedEffect(Unit){
        viewModel.loadNotes()
    }
    TwoPaneLayout(
        secondaryPaneAnimationState = openedNotes,
        showTopOrRightPane = openedNotes != null,
        props = TwoPaneProps(),
        leftPane = {
            NoteList(
                modifier = Modifier,
                notes = viewModel.notes.collectAsState().value,
                onDetailsOpen = viewModel::onNoteDetailsRequested,

                )
        },
        topOrRightPane = {
            if (openedNotes != null) {
                NoteDetails(note = openedNotes)
            }
        }
    )


}