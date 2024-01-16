package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.shared_notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.GenericListScreen
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digitaldiary.twozerotwothree.data.repository.created_note.NoteListItem

class NoteList(
    private val notes: List<NoteListItem>,
    private val onDetailsOpen: (String) -> Unit,
    private val onExitRequest:()->Unit,
) : Screen {
    @Composable
    override fun Content() {
        NoteList(
            modifier = Modifier,
            notes = notes,
            onDetailsOpen = onDetailsOpen,
            onExitRequest = onExitRequest
            )
    }

}

@Composable
internal fun NoteList(
    modifier: Modifier = Modifier,
    notes: List<NoteListItem>,
    onDetailsOpen: (String) -> Unit,
    onExitRequest:()->Unit,
) {
    val viewModel= remember {
        NoteListViewModel()
    }

    ProgressBarNSnackBarDecorator (
        snackBarMessage = viewModel.snackBarMessage.collectAsState().value,
        showProgressBar = viewModel.showProgressBar.collectAsState().value
    ){
        Scaffold(
            topBar = {
                SimpleTopBar(
                    title = "Shared Notes",
                    onNavigationIconClick =onExitRequest,
                )
            }
        ) {
            GenericListScreen(
                modifier = modifier.padding(it),
                items = notes
            ) { itemModifier, item ->
                NoteCard(
                    modifier = itemModifier.padding(8.dp),
                    title = item.title,
                    onClick = {
                        onDetailsOpen(item.id)

                    }

                )
            }
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
