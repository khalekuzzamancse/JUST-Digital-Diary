package notebook.ui.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.newui.SnackNProgressBarDecorator
import common.newui.TwoPaneNewUIPros
import common.ui.layout.TwoPaneLayout
import notebook.ui.note_details.component.DetailsOfNote
import notebook.ui.note_list.component.NoteList
import notebook.ui.note_list.route.NotesListViewModel


/**
 * @param backButtonHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
fun NotesAndDetailsRoute(
    modifier: Modifier = Modifier,
    onExitRequest: () -> Unit,
    backButtonHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {
    val viewModel = remember { NotesListViewModel() }
    val notes = viewModel.notes.collectAsState().value
    val openedNotes = viewModel.openedNote.collectAsState().value
    val showDetails = openedNotes != null
    val onNavigationIconClick: () -> Unit = {
        viewModel.onCloseDetailsRequested()
    }
    backButtonHandler {
        false
    }

    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }

    val navigationIcon =
        if (showDetails) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu
    val alignment = Alignment.TopStart
    val props = TwoPaneNewUIPros(
        showTopOrRightPane = showDetails,
        alignment = alignment,
        navigationIcon = navigationIcon
    )
    SnackNProgressBarDecorator(
        showProgressBar = viewModel.showProgressBar.collectAsState().value,
        onSnackBarCloseRequest = { }
    ) {
        common.newui.TwoPaneLayout(
            modifier = modifier,
            props = props,
            onNavigationIconClick = if (showDetails) onNavigationIconClick else onExitRequest,
            leftPane = {
                NoteList(
                    modifier = modifier,
                    notes = notes,
                    onDetailsOpen = {
                        viewModel.onNoteDetailsRequested(it)
                    }
                )
            },
            topOrRightPane = {
                if (openedNotes != null)
                    DetailsOfNote(modifier = modifier, note = openedNotes)
            },

            )
    }


}
