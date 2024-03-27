package notebook.data.sources.local

import database.local.api.NoteAPIs
import database.local.schema.notebook.NoteEntityLocal

class LocalDataSourceImpl: LocalDataSource {
    override suspend fun addNote(note: NoteEntityLocal):Result<Unit> {
     val result=NoteAPIs.addNote(note)
        return if (result.isSuccess)
            Result.success(Unit)
        else{
            val exception=result.exceptionOrNull()?: Throwable("Failed to save,Unknown error at :LocalDataSourceImpl\ncontact to developer")
            Result.failure(exception)
        }
    }

    override suspend fun getNotes(): Result<List<NoteEntityLocal>> {
       return NoteAPIs.retrieveNotes()
    }
}