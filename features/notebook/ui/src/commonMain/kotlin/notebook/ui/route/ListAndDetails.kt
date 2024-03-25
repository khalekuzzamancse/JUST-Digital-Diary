package notebook.ui.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.ui.layout.TwoPaneLayout
import notebook.ui.note_details.route.NoteDetails
import notebook.ui.note_list.route.ListOfNotes


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
