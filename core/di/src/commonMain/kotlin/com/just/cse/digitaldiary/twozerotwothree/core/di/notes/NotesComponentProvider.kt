package com.just.cse.digitaldiary.twozerotwothree.core.di.notes

import com.just.cse.digital_diary.two_zero_two_three.layers.data.notes.repository.NotesRepositoryImpl

/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [NotesRepositoryImpl]
 */
object NotesComponentProvider {
    fun getNotesRepository(): NotesRepositoryImpl {
        return NotesRepositoryImpl()
    }


}