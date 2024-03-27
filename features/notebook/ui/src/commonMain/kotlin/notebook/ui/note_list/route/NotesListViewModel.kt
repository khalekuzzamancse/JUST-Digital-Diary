package notebook.ui.note_list.route


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import notebook.data.DependencyFactory
import notebook.domain.model.NoteModel
import notebook.domain.repository.NotesRepository
import notebook.ui.note_details.component.Note

class NotesListViewModel {
    /**
     * if we passed the repo via constructor then the client need to access Repository,
     * but we do not want that,also we need to manage single place of instance creation for dependency inversion,
     * that is why using the Factory pattern
     */
    private val repository = DependencyFactory.repository()
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()
    private val _snackBarMessage = MutableStateFlow<String?>(null)
    val snackBarMessage = _snackBarMessage.asStateFlow()
    private val _notes = MutableStateFlow(emptyList<Note>())
    val notes = _notes.asStateFlow()
    private val _openedNote = MutableStateFlow<Note?>(null)
    val openedNote = _openedNote.asStateFlow()

    suspend fun loadNotes() {
        _showProgressBar.update { true }
        val notes = repository.getNotes()
            .getOrDefault(emptyList())
            .map(::toNote)
        _notes.update { notes }
        _showProgressBar.update { false }
    }
    fun getNotes(id: String): Note?{
      return  _notes.value.find { it.id == id }
    }

    fun onNoteDetailsRequested(id: String) {
        scope.launch {
            val alreadyNoteOpened = _openedNote.value != null
            if (alreadyNoteOpened) {
                onCloseDetailsRequested()
            }
            _openedNote.update {
                _notes.value.find { it.id == id }
            }


        }
    }

    fun onCloseDetailsRequested() {
        _openedNote.update { null }
    }

}
private fun toNote(model:NoteModel)=Note(
    id =model.id,
    title = model.title,
    description = model.description,
    timeStamp = model.timeStamp
)