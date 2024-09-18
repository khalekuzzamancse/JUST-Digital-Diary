package notebook.ui.notelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.list.AdaptiveList
import notebook.ui.notedetails.Note

@Composable
 fun NoteList(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onDetailsOpen: (String) -> Unit,
) {
            AdaptiveList(
                modifier = modifier,
                items = notes,
                contentPadding = PaddingValues(4.dp),
                verticalItemSpacing = 8.dp,

            ) {  item ->
                NoteCard(
                    modifier = Modifier,
                    note=item,
                    onClick = {
                        onDetailsOpen(item.id)
                    }
                )
            }
        }




