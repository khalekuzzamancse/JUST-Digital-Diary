package common.newui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/*
 * Following the single responsibility
 * so it will responsible for only showing the snack-bar
 * to it has reason to change when the snack-bar logic needed to be changed
 *
 *
 */
/**
 * Encapsulate the state or proper so adding  or remove or passing through multiple level
 * can done easily
 */
data class CustomSnackBarData(
    val message: String,
    val details: String? = null,
    val isError: Boolean = false,
)

/**


 */
@Composable
fun BoxScope.SnackBarDecorator(
    data: CustomSnackBarData,
    onSnackBarCloseRequest: () -> Unit,
) {
    Box(
        modifier = Modifier.padding(8.dp).align(Alignment.BottomCenter)
    ) {
        Column {
            CustomSnackBar(
                data, onSnackBarCloseRequest
            )
            Spacer(Modifier.height(32.dp))
        }

    }

}


@Composable
private fun CustomSnackBar(
    data: CustomSnackBarData,
    onDetailsClosed: () -> Unit,
) {
    //okay to store the state here,since this state is not so  important
    //storing state here will make client code simpler
    var isDialogOpen by remember { mutableStateOf(false) }
    var detailsButtonNotClick by remember { mutableStateOf(true) }
    LaunchedEffect(detailsButtonNotClick){
        delay(4_000)
        if (detailsButtonNotClick)
            onDetailsClosed()
    }
    Box(Modifier.background(Color.Black)) {
        _CustomSnackBar(data) {
            detailsButtonNotClick=false
            isDialogOpen = true
        }
        if (isDialogOpen) {
            _CustomDialog(isDialogOpen = true, details = data.details) {
                isDialogOpen = false
                onDetailsClosed()
            }
        }
    }


}

@Composable
private fun _CustomSnackBar(data: CustomSnackBarData, onActionClicked: () -> Unit) {
    Snackbar(
        modifier = Modifier.background(Color.Red),
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
private fun _CustomDialog(
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

