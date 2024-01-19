package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CompactModeLayout(
    noteList: @Composable () -> Unit,
    noteDetails: (@Composable () -> Unit)?=null,
) {
    Box(Modifier.fillMaxSize()) {
        noteList()
        if (noteDetails != null){
            Box(Modifier.matchParentSize()){
                noteDetails()
            }
        }


    }


}