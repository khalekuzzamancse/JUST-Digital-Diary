package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.note_details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.common.CreateNoteTopBar
import com.just.cse.digitaldiary.twozerotwothree.data.data.created_note.DummyNoteDataProvider
import com.just.cse.digitaldiary.twozerotwothree.data.data.created_note.Note

class NoteDetailDestination(
    private val note:Note,
   private val onExitRequest:()->Unit,
):Screen{
    @Composable
    override fun Content() {
        NoteDetails(
            note = note,
            onExitRequest = onExitRequest
        )
    }

}
@Composable
fun NoteDetails(
    note:Note,
    onExitRequest:()->Unit,
) {
    Scaffold (
        topBar = {
            CreateNoteTopBar(
                title = "Note Details",
                onNavigationIconClick =onExitRequest
            )
        },

    ){
        NoteDetails(
            modifier = Modifier.padding(it),
            title = note.title,
            description = note.description,
            timeString = note.timeString,
            creatorName = note.creatorName,
            creatorImageUrl = note.creatorImageUrl,
            creatorMoreInfo = note.creatorMoreInfo
        )


    }
}