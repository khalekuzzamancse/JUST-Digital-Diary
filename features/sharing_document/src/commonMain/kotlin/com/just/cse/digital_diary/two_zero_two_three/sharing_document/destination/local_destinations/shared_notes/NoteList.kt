package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.shared_notes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digitaldiary.twozerotwothree.data.repository.created_note.NoteListItem

class NoteList(
    private val notes: List<NoteListItem>,
    private val onDetailsOpen: (String) -> Unit,
    private val onExitRequest:()->Unit,
) : Screen {
    @Composable
    override fun Content() {
        NoteListDestination(
            modifier = Modifier,
            notes = notes,
            onDetailsOpen = onDetailsOpen,
            onExitRequest = onExitRequest
            )
    }

}

