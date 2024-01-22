package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.note_details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digitaldiary.twozerotwothree.data.repository.created_note.model.Note

/**
 * * Show the details of a a selected note.
 * * It is it is internal to the module can outer module can not directly access it.
 * * It this can handle both compact,medium,expanded window size.
 * * It take the [Note] and break it down to primitive and delegate to the [NoteDetails]
 * * So this is coupled with the [Note]
 * @param note that details is going to show
 * @param onExitRequest will be called to exit the from note creation screen
 */
@Composable
internal fun NoteDetails(
    note: Note,
    onExitRequest:()->Unit,
) {
    Scaffold (
        topBar = {
            SimpleTopBar(
                title = "Note Details",
                onNavigationIconClick =onExitRequest
            )
        },

    ){
        NoteDetails(
            modifier = Modifier.padding(it),
            title = note.title,
            description = note.description,
            timeStamp = note.timeString,
            creatorName = note.creatorName,
            creatorImageUrl = note.creatorImageUrl,
            creatorMoreInfo = note.creatorMoreInfo
        )


    }
}