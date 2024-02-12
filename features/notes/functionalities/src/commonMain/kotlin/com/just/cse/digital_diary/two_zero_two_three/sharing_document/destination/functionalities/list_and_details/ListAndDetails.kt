package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.list_and_details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
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
    onExitRequest: () -> Unit,
    backButtonHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {
    val showDetails = selectedNoteId != null
    backButtonHandler {
        if (showDetails) {
            onNoteDetailsCloseRequest()
            true
        } else
            false
    }
    TwoPaneLayout(
        modifier = Modifier,
        navigationIcon = if (showDetails) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu,
        onNavigationIconClick = if (showDetails) onNoteDetailsCloseRequest else onExitRequest,
        leftPane = {
            ListOfNotes(
                modifier = modifier,
                onDetailsRequest = onDetailsRequest
            )
        },
        topOrRightPane = {
            NoteDetails(modifier = Modifier, id = "1")
        },
        alignment = Alignment.TopStart,
        showTopOrRightPane = showDetails
    )


}
