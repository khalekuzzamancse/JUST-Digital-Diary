package notebook.domain.repository

import notebook.domain.model.NoteModel

interface  NotesRepository {
    suspend fun addNote(model: NoteModel):Result<NoteModel>
    suspend fun getNotes(): Result<List<NoteModel>>

}