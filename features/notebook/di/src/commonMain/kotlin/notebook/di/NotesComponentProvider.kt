package notebook.di

import notebook.data.repository.NotesRepositoryImpl


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [NotesRepositoryImpl]
 */
object NotesComponentProvider {
    fun getNotesRepository(): NotesRepositoryImpl {
        return NotesRepositoryImpl()
    }


}