package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.note_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.note_list.NoteList
import com.just.cse.digitaldiary.twozerotwothree.core.di.notes.NotesComponentProvider


@Composable
fun ListOfNotes(
    modifier: Modifier = Modifier,
    onDetailsRequest: (String) -> Unit,
) {
    val viewModel = remember { NotesListViewModel(NotesComponentProvider.getNotesRepository()) }
    val openedNotes = viewModel.openedNote.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }
    NoteList(
        modifier = modifier,
        notes = viewModel.notes.collectAsState().value,
        onDetailsOpen = onDetailsRequest
    )
//    TwoPaneLayout(
//        secondaryPaneAnimationState = openedNotes,
//        showTopOrRightPane = openedNotes != null,
//        props = TwoPaneProps(),
//        leftPane = {
//
//        },
//        topOrRightPane = {
//            if (openedNotes != null) {
//                NoteDetails(
//                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondaryContainer),
//                    note = openedNotes
//                )
//            }
//        }
//    )


}