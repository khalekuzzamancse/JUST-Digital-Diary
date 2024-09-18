package realm.test

import database.local.api.NoteAPIs
import database.local.schema.notebook.NoteEntityLocal
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NoteAPIsTest {
    @Test
    fun `add NoteEntity test`() {
        runBlocking {
            val requestModel = NoteEntityLocal(
                id = "1",
                title = "Test Note",
                description = "This is a test note.",
                timeStamp = "2022-01-01T00:00:00Z"
            )
            val responseModel = NoteAPIs.addNote(requestModel)
            println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }
    }

    @Test
    fun `add and retrieve  NoteEntity test`() {
        runBlocking {
            val requestModels = listOf(
                NoteEntityLocal(
                    id = "1",
                    title = "Test Note 1",
                    description = "This is test note 1.",
                    timeStamp = "2022-01-01T00:00:00Z"
                ),
                NoteEntityLocal(
                    id = "2",
                    title = "Test Note 2",
                    description = "This is test note 2.",
                    timeStamp = "2022-01-02T00:00:00Z"
                )
            )
            NoteAPIs.addNotes(requestModels)
            val responseModel = NoteAPIs.retrieveNotes()
            println(responseModel)
            assertTrue(responseModel.getOrDefault(emptyList()).size >= 2)
        }

    }
}


