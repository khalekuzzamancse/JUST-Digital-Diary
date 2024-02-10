package com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.common_ui.TopBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.functionalities.create_note.CreateNoteDestination

class CreateNoteScreen(
 private val   onExitRequest:()->Unit,
):Screen {
    @Composable
    override fun Content() {
        TopBarDecorator (
            topNavigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onNavigationIconClick =onExitRequest
        ){
            CreateNoteDestination(modifier = Modifier.padding(it))
        }

    }
}