package com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.create_note.CreateNoteSection

@Composable
fun NoteCreation(
    onExitRequest: () -> Unit,
) {
    CreateNoteSection(onExitRequest = onExitRequest)
}