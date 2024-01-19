package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.shared_notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digitaldiary.twozerotwothree.data.repository.created_note.NoteListItem

@Composable
internal fun NoteListDestination(
    modifier: Modifier = Modifier,
    notes: List<NoteListItem>,
    onDetailsOpen: (String) -> Unit,
    onExitRequest:()->Unit,
) {

        Scaffold(
            topBar = {
                SimpleTopBar(
                    title = "Shared Notes",
                    onNavigationIconClick =onExitRequest,
                )
            }
        ) {
            AdaptiveList(
                modifier = modifier.padding(it),
                items = notes
            ) {  item ->
                NoteCard(
                    modifier = Modifier.padding(8.dp),
                    title = item.title,
                    onClick = {
                        onDetailsOpen(item.id)

                    }

                )
            }
        }



}

@Composable
private fun NoteCard(
    modifier: Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth().clickable {
            onClick()
        },
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }

}
