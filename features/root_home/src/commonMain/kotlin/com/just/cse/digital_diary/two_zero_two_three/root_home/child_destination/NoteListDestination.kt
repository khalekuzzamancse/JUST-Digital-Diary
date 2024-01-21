package com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point.NotesScreen


class NoteListDestination(
    private val onExitRequest:()->Unit
):Screen{
    @Composable
    override fun Content() {
        NotesScreen(onExitRequest=onExitRequest)

    }

}
