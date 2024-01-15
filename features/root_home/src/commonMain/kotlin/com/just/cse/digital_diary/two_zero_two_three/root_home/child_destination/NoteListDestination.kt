package com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.shared_notes.NoteListNavGraph


class NoteListDestination(
    private val onExitRequest:()->Unit
):Screen{
    @Composable
    override fun Content() {
        NoteListNavGraph(
            onExitRequest=onExitRequest
        )

    }

}
