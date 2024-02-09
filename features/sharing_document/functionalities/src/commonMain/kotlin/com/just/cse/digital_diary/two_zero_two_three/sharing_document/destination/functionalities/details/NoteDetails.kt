package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.note_details.DetailsOfNote
import com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.note_details.Note
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.note_list.NotesListViewModel
import com.just.cse.digitaldiary.twozerotwothree.core.di.notes.NotesComponentProvider

private val dummyNote = Note("A", title = "Baaf", description = "Jfjafj", timeStamp = "13 July")

@Composable
fun NoteDetails(
    modifier: Modifier = Modifier,
    id: String,
) {
    val viewModel = remember { NotesListViewModel(NotesComponentProvider.getNotesRepository()) }
    val note = viewModel.getNotes(id)
    DetailsOfNote(modifier = modifier, note = dummyNote)


}
