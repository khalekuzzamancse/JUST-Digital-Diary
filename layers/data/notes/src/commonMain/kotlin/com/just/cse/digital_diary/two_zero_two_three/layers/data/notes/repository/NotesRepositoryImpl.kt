package com.just.cse.digital_diary.two_zero_two_three.layers.data.notes.repository

import com.just.cse.digital_diary.two_zero_two_three.layers.data.notes.data_sources.local.entity.NoteEntity
import com.just.cse.digital_diary.two_zero_two_three.layers.data.notes.data_sources.local.source.NotesLocalDataSource
import com.just.cse.digital_diary.two_zero_two_three.layers.domain.notes.model.NoteModel
import com.just.cse.digital_diary.two_zero_two_three.layers.domain.notes.repository.NotesRepository

class NotesRepositoryImpl : NotesRepository {
    override suspend fun addNote(model: NoteModel): Result<NoteModel> {
        val res = NotesLocalDataSource().addNote(
            NoteEntity(
                id =model.title, title = model.title,
                description = model.description,
                timeStamp = model.timeStamp
            )
        )
        return if (res.isSuccess)
            Result.success(model)
        else Result.failure(Throwable(message = "Failed to add"))
    }

    override suspend fun getNotes(): Result<List<NoteModel>> {
        val res=NotesLocalDataSource().getNotes()
        return if (res.isSuccess){
            Result.success(res.getOrDefault(emptyList()).map { NoteModel(it.title,it.description,it.timeStamp) })
        } else Result.failure(Throwable("Failed to Load List"))
    }
}