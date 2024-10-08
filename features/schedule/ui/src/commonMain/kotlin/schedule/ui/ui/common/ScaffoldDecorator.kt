package schedule.ui.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
 internal fun ScaffoldDecorator(
    modifier: Modifier = Modifier,
    onAddButtonClick: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            Button(
                onClick = onAddButtonClick,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                Spacer(Modifier.width(4.dp))
                Text("Add")
            }
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            content(Modifier.padding(innerPadding))
        }

    }
}