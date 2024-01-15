package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.shared_notes

import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.create_note.CreateNoteData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteListViewModel {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()
    private val _snackBarMessage = MutableStateFlow<String?>(null)
    val snackBarMessage = _snackBarMessage.asStateFlow()
    init {
        scope.launch {
            _showProgressBar.update { true }
            delay(500)
            _showProgressBar.update { false }
            _snackBarMessage.update { "Loaded Successfully" }
            delay(1000)
            _snackBarMessage.update { null }
        }
    }
}