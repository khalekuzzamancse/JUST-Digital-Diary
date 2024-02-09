package com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.routes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
internal fun NoteListTopBarDecorator(
    onNoteCreateRequest:()->Unit={},
    content: @Composable (PaddingValues) -> Unit={},
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            SimpleTopBar(
                title = "Notes",
                navigationIcon = Icons.Filled.Menu,
                onNavigationIconClick = {}
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = onNoteCreateRequest) {
                Row(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        content(it)
    }
}