package com.just.cse.digital_diary.two_zero_two_three.layers.domain.notes.repository

import com.just.cse.digital_diary.two_zero_two_three.layers.domain.notes.model.NoteModel

interface  NotesRepository {
    suspend fun addNote(model:NoteModel):Result<NoteModel>
    suspend fun getNotes(): Result<List<NoteModel>>

}