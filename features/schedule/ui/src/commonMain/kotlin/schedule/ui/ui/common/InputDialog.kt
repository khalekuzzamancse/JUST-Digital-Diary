package schedule.ui.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
internal fun InputDialog(
    enabledDone: Boolean,
    onDismiss: () -> Unit,
    onDone: () -> Unit,
    content: @Composable () -> Unit

) {

    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            content()

        },
        confirmButton = {
            TextButton(
                onClick =onDone,
                enabled =enabledDone
            ) {
                Text("Done")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
