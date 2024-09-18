package notebook.ui.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.newui.SnackNProgressBarDecorator
import common.newui.TwoPaneNewUIPros
import notebook.ui.notedetails.DetailsOfNote
import notebook.ui.notedetails.Note
import notebook.ui.notelist.NoteList


/**
 * @param backButtonHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
fun NotesAndDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: NotesListViewModel,
    onExitRequest: () -> Unit,
    backButtonHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {

    backButtonHandler {
        false
    }

    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }

    SnackNProgressBarDecorator(
        showProgressBar = viewModel.showProgressBar.collectAsState().value,
        onSnackBarCloseRequest = { }
    ) {
        _NoteNDetailsRaw(
            modifier=modifier,
            notes=viewModel.notes.collectAsState().value,
            openedNote=viewModel.openedNote.collectAsState().value,
            onDetailsRequest = viewModel::onNoteDetailsRequested,
            onDetailsCloseRequest = viewModel::onCloseDetailsRequested,
            onExitRequest= onExitRequest
        )
    }


}

@Composable
private fun _NoteNDetailsRaw(
    modifier: Modifier= Modifier,
    notes:List<Note>,
    openedNote: Note?,
    onDetailsRequest:(String)->Unit,
    onDetailsCloseRequest:()->Unit,
    onExitRequest:()->Unit
) {
    val showDetails=openedNote!=null
    val navigationIcon = if (showDetails) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu
    val alignment = Alignment.TopStart
    val props = TwoPaneNewUIPros(
        showTopOrRightPane = showDetails,
        alignment = alignment,
        navigationIcon = navigationIcon
    )
    common.newui.TwoPaneLayout(
        modifier = modifier,
        props = props,
        onNavigationIconClick = if (showDetails) onDetailsCloseRequest else onExitRequest,
        leftPane = {
            NoteList(
                modifier = modifier,
                notes = notes,
                onDetailsOpen =onDetailsRequest
            )
        },
        topOrRightPane = {
            if (openedNote != null) {
                DetailsOfNote(modifier = modifier, note = openedNote)
            }
        },

        )

}