package com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.routes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.details.NoteDetails
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.note_list.ListOfNotes

@Composable
fun ListAndDetails(
    selectedNoteId: String?,
    onDetailsRequest: (String) -> Unit,
) {
    if (selectedNoteId != null) {
        Row(Modifier.fillMaxWidth()) {
            ListOfNotes(modifier = Modifier.weight(1f), onDetailsRequest = {})
            NoteDetails(modifier = Modifier.weight(1f), id = "1")
        }
    } else {
        ListOfNotes(modifier = Modifier, onDetailsRequest = onDetailsRequest)
    }


}