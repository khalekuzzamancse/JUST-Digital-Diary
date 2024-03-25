package notebook.ui.note_list.route


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import notebook.domain.repository.NotesRepository
import notebook.ui.note_details.component.Note

class NotesListViewModel(
    private val repository: NotesRepository
) {
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
        val notes = repository.getNotes()
            .getOrDefault(emptyList())
            .mapIndexed { index, value ->
                Note(
                    id = "$index",
                    title = value.title,
                    description = value.description,
                    timeStamp = value.timeStamp
                )
            }
        _notes.update { notes }


    }
    fun getNotes(id: String): Note?{
      return  _notes.value.find { it.id == id }
    }

    fun onNoteDetailsRequested(id: String) {
        scope.launch {
            val alreadyNoteOpened = _openedNote.value != null
            if (alreadyNoteOpened) {
                onCloseDetailsRequested()
                delay(100)//delay to clear the old opened note in non expanded mode

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