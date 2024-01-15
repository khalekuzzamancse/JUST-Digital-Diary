package com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.module_entry_point.NoteCreation


class CreateNoteScreen: Screen {
    @Composable
    override fun Content() {
        val navigator= LocalNavigator.current
       CreateNoteDestination(
           onBackArrowClick = {
               navigator?.pop()
           }
       )
    }

}
@Composable
fun CreateNoteDestination(
    onBackArrowClick: () -> Unit,
) {
    NoteCreation(onExitRequest = onBackArrowClick)
}

