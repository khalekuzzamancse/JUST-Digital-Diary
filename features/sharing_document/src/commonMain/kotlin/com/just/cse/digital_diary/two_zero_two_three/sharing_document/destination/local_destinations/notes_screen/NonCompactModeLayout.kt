package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.notes_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NonCompactModeLayout(
    noteList: @Composable () -> Unit,
    noteDetails: (@Composable () -> Unit)?=null,
) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(Modifier.weight(1f, false)) { noteList() }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                if (noteDetails != null) {
                    AnimatedVisibility(visible = true){
                        noteDetails()
                    }

                }
            }
        }
    }



