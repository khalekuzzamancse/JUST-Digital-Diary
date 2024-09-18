package notebook.ui.create_note.route

import common.newui.CustomSnackBarData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import notebook.data.DependencyFactory
import notebook.domain.model.NoteModel
import notebook.ui.create_note.component.Note
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CreateNoteViewModel {
    /**
     * if we passed the repo via constructor then the client need to access Repository,
     * but we do not want that,also we need to manage single place of instance creation for dependency inversion,
     * that is why using the Factory pattern
     */
    private val repository = DependencyFactory.repository()
    private val scope = CoroutineScope(Dispatchers.Default)
    private val _data = MutableStateFlow(
        Note(title = "", description = "")
    )
    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()
    private val _snackBarMessage = MutableStateFlow<CustomSnackBarData?>(null)
    val snackBarMessage = _snackBarMessage.asStateFlow()
    val data = _data.asStateFlow()
    fun onTitleChanged(title: String) {
        _data.update { it.copy(title = title) }
    }

    fun onDescriptionChanged(description: String) {
        _data.update { it.copy(description = description) }
    }

    fun clearSnackBarMessage() {
        _snackBarMessage.update { null }
    }

     fun onCreateRequest() {
         scope.launch {
             _showProgressBar.update { true }
             _data.update { it.copy(timeStamp = currentTime) }
             val response = repository.addNote(_data.value.toModel())
             _showProgressBar.update { false }
             if (response.isSuccess) {
                 _snackBarMessage.update {
                     CustomSnackBarData(message = "Saved Successfully")
                 }
             } else {
                 val exception = response.exceptionOrNull()?.message
                 _snackBarMessage.update {
                     CustomSnackBarData(message = "Failed to save", details = exception, isError = true)
                 }
             }
             println(
                 repository.getNotes().getOrDefault(emptyList())
             )

         }

    }

    private val currentTime: String
        get() {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
            return current.format(formatter).toString()
        }

}

private fun Note.toModel() = NoteModel(
    id = title + timeStamp,
    title = this.title,
    description = this.description,
    timeStamp = this.timeStamp
)