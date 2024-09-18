package database.local.api

import database.local.DB
import database.local.DB.retrieveEntities
import database.local.schema.notebook.NoteEntityLocal
import database.local.schema.notebook.NoteSchema
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

object NoteAPIs {

    private val db = DB.db

    suspend fun addNote(model: NoteEntityLocal): Result<NoteEntityLocal> {
        val result = DB.addEntity(NoteSchema().apply {
            this.id = model.id
            this.title = model.title
            this.description = model.description
            this.timeStamp = model.timeStamp
        }) { this.toEntity() }
        return result
    }

    suspend fun addNotes(models: List<NoteEntityLocal>) {
        models.map { model -> CoroutineScope(Dispatchers.Default).async { addNote(model) } }
            .awaitAll()
    }

    fun retrieveNotes(): Result<List<NoteEntityLocal>> {
        return retrieveEntities(
            query = { db.query<NoteSchema>().find() },
            transform = NoteSchema::toEntity
        )
    }
}
