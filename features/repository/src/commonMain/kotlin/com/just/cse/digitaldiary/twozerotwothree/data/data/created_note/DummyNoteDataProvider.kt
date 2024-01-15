package com.just.cse.digitaldiary.twozerotwothree.data.data.created_note

import androidx.compose.ui.text.intl.Locale
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DummyNoteDataProvider {
    private val description="In this example, I've used the System.currentTimeMillis() to get the current " +
            "time in milliseconds and then adjusted the timestamps for each note by subtracting a certain " +
            "number of milliseconds to simulate different timestamps. Adjust the values as needed for your specific use case."
    val notes=listOf(
        Note(
            title = "Datastructures and Algorithms",
            description = description,
            timeString = "01-01-2023 10:30:00 AM",
            creatorName = "John Doe",
            creatorImageUrl = "https://example.com/john-doe.jpg",
            creatorMoreInfo = "Student of CSE"
        ),
        Note(
            title = "Note 2",
            description = "Description for Note 2",
            timeString = "01-01-2023 10:30:00  PM",
            creatorName = "Jane Smith",
            creatorImageUrl = "https://example.com/jane-smith.jpg",
            creatorMoreInfo = "More info about Jane Smith"
        ),
        Note(
            title = "Note 3",
            description = "Description for Note 3",
            timeString = "01-01-2023 10:30:00 AM",
            creatorName = "Alice Johnson",
            creatorImageUrl = "https://example.com/alice-johnson.jpg",
            creatorMoreInfo = "More info about Alice Johnson"
        ),
    )
    fun getDummyNoteList(): List<Note> {
        return notes
    }
    fun getNoteListItem()= notes.map {
        NoteListItem(
            title = it.title,
            id = it.id
        )
    }
    fun getNoteById(noteId: String)=notes.find { it.id==noteId }
}