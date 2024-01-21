package com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.create_note.CreateNoteDestination

/**
 * * This the Entry point and Exit point to create a new note and share it or save it locally
 * * It delegates to the [CreateNoteDestination]
 * @param onExitRequest will be called to exit the from note creation screen

 */
@Composable
fun NoteCreationScreen(
    onExitRequest: () -> Unit,
) {
    CreateNoteDestination(onExitRequest = onExitRequest)
}