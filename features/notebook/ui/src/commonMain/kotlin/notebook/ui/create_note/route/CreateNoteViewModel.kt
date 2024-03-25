package notebook.ui.create_note.route

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import notebook.ui.create_note.component.CreateNoteData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CreateNoteViewModel {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val _data = MutableStateFlow(
        CreateNoteData(
            title = "",
            description = ""
        )
    )

    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()
    private val _snackBarMessage = MutableStateFlow<String?>(null)
    val snackBarMessage = _snackBarMessage.asStateFlow()
    val data = _data.asStateFlow()
    fun onTitleChanged(title: String) {
        _data.update { it.copy(title = title) }
    }

    fun onDescriptionChanged(description: String) {
        _data.update { it.copy(description = description) }
    }

    fun onCreateRequest() {
        scope.launch {
            _data.update { it.copy(timeStamp = currentTime) }
            println("onCreateRequest:${_data.value}")
            _showProgressBar.update { true }
            delay(2000)
            _showProgressBar.update { false }
            delay(100)
            _snackBarMessage.update { "Not Implemented Yet" }
            delay(2000)
            _snackBarMessage.update { null }

        }

    }

    private val currentTime: String
        get() {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
            return current.format(formatter).toString()
        }

}