package notebook.ui.note_details.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import notebook.ui.note_list.route.NotesListViewModel
import notebook.di.NotesComponentProvider
import notebook.ui.note_details.component.DetailsOfNote
import notebook.ui.note_details.component.Note


private val dummyNote = Note("A", title = "Baaf", description = "Jfjafj", timeStamp = "13 July")

@Composable
fun NoteDetails(
    modifier: Modifier = Modifier,
    id: String,
) {
    val viewModel = remember { NotesListViewModel() }
    val note = viewModel.getNotes(id)
    DetailsOfNote(modifier = modifier, note = dummyNote)


}
