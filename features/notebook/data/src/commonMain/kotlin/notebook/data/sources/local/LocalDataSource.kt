package notebook.data.sources.local

import database.local.schema.notebook.NoteEntityLocal

interface LocalDataSource {
    suspend fun addNote(mode: NoteEntityLocal):Result<Unit>
    suspend fun getNotes(): Result<List<NoteEntityLocal>>
}