package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen.NotesScreenViewModel
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point.NotesScreen

@Composable
internal fun NoteListDestination(
    viewModel: NotesScreenViewModel,
    onExitRequest: () -> Unit,
) {
    NotesScreen( onExitRequest = onExitRequest)

}