package notebook.data.data_sources.local.source

import notebook.data.data_sources.local.entity.NoteEntity

class NotesLocalDataSource {
    suspend fun addNote(mode: NoteEntity): Result<Boolean> {
        return Result.success(true)
    }

    suspend fun getNotes(): Result<List<NoteEntity>> {
        return Result.success(dummyNoteList)
    }
   private val dummyNoteList = listOf(
        NoteEntity(
            id = "1",
            title = "Meeting Notes",
            description = "Discuss project updates and timelines",
            timeStamp = "2024-02-08 10:00 AM"
        ),
        NoteEntity(
            id = "2",
            title = "Shopping List",
            description = "Milk, eggs, bread, and vegetables",
            timeStamp = "2024-02-08 02:30 PM"
        ),
        NoteEntity(
            id = "3",
            title = "Ideas for Presentation",
            description = "Brainstorming ideas for the upcoming presentation",
            timeStamp = "2024-02-09 09:15 AM"
        ),

    )

}