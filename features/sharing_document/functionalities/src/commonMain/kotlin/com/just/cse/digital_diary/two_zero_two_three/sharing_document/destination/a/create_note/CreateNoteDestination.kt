package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.a.create_note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.create_note.CreateNote

/**
 * * It used to create  the Notes
 * * It is it is internal to the module can outer module can not directly access it.
 * * It this can handle both compact,medium,expanded window size.
 *    be called to exit the from note creation screen
 */
@Composable
fun CreateNoteDestination() {
    val viewModel= remember { CreateNoteViewModel() }
    CreateNote(
        data = viewModel.data.collectAsState().value,
        onTitleChanged = viewModel::onTitleChanged,
        onDescriptionChanged = viewModel::onDescriptionChanged,
        onCreate = viewModel::onCreateRequest,
        onExitRequest = {}
    )

}