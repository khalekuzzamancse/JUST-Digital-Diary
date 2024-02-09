package com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.routes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.details.NoteDetails
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.note_list.ListOfNotes


/**
 * @param backButtonHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
fun NotesAndDetailsRoute(
    modifier: Modifier = Modifier,
    selectedNoteId: String?,
    onNoteDetailsCloseRequest: () -> Unit,
    onDetailsRequest: (String) -> Unit,
    backButtonHandler:@Composable (onBack: () -> Unit)->Unit,
) {
    WindowSizeDecorator(
        modifier = modifier,
        onCompact = {
            selectedNoteId.let { id ->
                if (id != null) {
                    NoteDetails(
                        modifier = Modifier, id = id
                    )
                 backButtonHandler{
                     onNoteDetailsCloseRequest()
                 }
                } else {
                    ListOfNotes(
                        modifier = modifier,
                        onDetailsRequest = onDetailsRequest
                    )
                }
            }

        },
        onNonCompact = {
            ListAndDetails(
                selectedNoteId = selectedNoteId,
                onDetailsRequest = onDetailsRequest
            )
            backButtonHandler{
                onNoteDetailsCloseRequest()
            }
        }
    )

}

@Composable
private fun ListAndDetails(
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