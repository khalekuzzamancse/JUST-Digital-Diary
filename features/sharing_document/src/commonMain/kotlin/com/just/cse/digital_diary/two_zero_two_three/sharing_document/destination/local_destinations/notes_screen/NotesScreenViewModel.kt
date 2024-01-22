package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen

import com.just.cse.digitaldiary.twozerotwothree.data.repository.created_note.data.DummyNoteDataProvider
import com.just.cse.digitaldiary.twozerotwothree.data.repository.created_note.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesScreenViewModel {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()
    private val _snackBarMessage = MutableStateFlow<String?>(null)
    val snackBarMessage = _snackBarMessage.asStateFlow()
    private val _notes=MutableStateFlow(DummyNoteDataProvider.getNoteListItem())
    val notes=_notes.asStateFlow()
    private val _openedNote=MutableStateFlow<Note?>(null)
    val openedNote=_openedNote.asStateFlow()

    fun onNoteDetailsRequested(noteId: String){
        scope.launch {
            val alreadyNoteOpened=_openedNote.value!=null
            if (alreadyNoteOpened)
            {
                onCloseDetailsRequested()
                delay(100)//delay to clear the old opened note in non expanded mode

            }
            DummyNoteDataProvider.getNoteById(noteId)?.let { note ->
                _openedNote.update { note }
        }


    }}
    fun onCloseDetailsRequested(){
        _openedNote.update { null }
    }

}