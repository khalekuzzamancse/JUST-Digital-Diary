package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point.NoteCreationScreen

@Composable
internal fun CreateNoteDestination(
    onExitRequest: () -> Unit,
) {
    NoteCreationScreen(onExitRequest = onExitRequest)

}