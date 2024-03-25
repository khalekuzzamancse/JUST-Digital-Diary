package notebook.ui.note_list.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import notebook.di.NotesComponentProvider
import notebook.ui.note_list.component.NoteList


@Composable
fun ListOfNotes(
    modifier: Modifier = Modifier,
    onDetailsRequest: (String) -> Unit,
) {
    val viewModel = remember { NotesListViewModel(NotesComponentProvider.getNotesRepository()) }
    val openedNotes = viewModel.openedNote.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }
    NoteList(
        modifier = modifier,
        notes = viewModel.notes.collectAsState().value,
        onDetailsOpen = onDetailsRequest
    )
//    TwoPaneLayout(
//        secondaryPaneAnimationState = openedNotes,
//        showTopOrRightPane = openedNotes != null,
//        props = TwoPaneProps(),
//        leftPane = {
//
//        },
//        topOrRightPane = {
//            if (openedNotes != null) {
//                NoteDetails(
//                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondaryContainer),
//                    note = openedNotes
//                )
//            }
//        }
//    )


}