package common.ui.snackbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data class SnackBarData(
    val message: String,
    val details: String? = null,
    val isError: Boolean = false
)

@Composable
fun CustomSnackBar(
    data: SnackBarData
) {
    //okay to store the state here,since this state is not so  important
    //storing state here will make client code simpler
    var isDialogOpen by remember { mutableStateOf(false) }
    _CustomSnackBar(data) {
        isDialogOpen = true
    }
    CustomDialog(isDialogOpen = isDialogOpen, details = data.details) {
        isDialogOpen = false
    }
}

@Composable
private fun _CustomSnackBar(data: SnackBarData, onActionClicked: () -> Unit) {
    Snackbar(
        action = {
            if (data.details != null) {
                IconButton(onClick = onActionClicked) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary//high importance since clickable
                    )
                }
            }
        },
        content = {
            val textColor = if (data.isError) Color.Red else Color.Green
            Text(text = data.message, color = textColor)
        }
    )
}

@Composable
private fun CustomDialog(
    isDialogOpen: Boolean,
    details: String?,
    onClose: () -> Unit
) {
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = onClose,
            confirmButton = {
                Button(onClick = onClose) {
                    Text("Close")
                }
            },
            text = {
                Text("$details")
            }
        )
    }
}

