package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.shared_notes

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.note_details.NoteDetailDestination
import com.just.cse.digitaldiary.twozerotwothree.data.data.created_note.DummyNoteDataProvider

@Composable
fun NoteListNavGraph(
    onExitRequest:()->Unit
) {
    val navigator = LocalNavigator.current
    val openDetails: (String) -> Unit = {
        println("OnDetailsRequest:$it")
        DummyNoteDataProvider.getNoteById(it)?.let { note ->
           navigator?.push(
               NoteDetailDestination(
                   note = note,
                   onExitRequest = {
                       navigator.pop()
                   }
               )
           )
        }
    }
    Navigator(
        NoteList(
            notes = DummyNoteDataProvider.getNoteListItem(),
            onDetailsOpen = openDetails,
            onExitRequest = onExitRequest
        )
    ){
        SlideTransition(it)
    }



}