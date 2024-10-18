package common.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

/**
 * - Hide the soft keyboard automatically
 */
@Composable
fun InsertButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current
    Button(
        onClick = {
            keyboard?.hide()
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
    ) {
        Icon(Icons.Outlined.Add, contentDescription = "insert")
        Spacer(Modifier.width(6.dp))
        Text(
            text = "Insert",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

/**
 * - Hide the soft keyboard automatically
 */
@Composable
fun UpdateButton(
    modifier: Modifier = Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth(),
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current
    Button(
        onClick = {
            keyboard?.hide()
            onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
    ) {
        Icon(Icons.Outlined.Update, contentDescription = "update")
        Spacer(Modifier.width(6.dp))
        Text(
            text = "Update",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}


@Composable
fun BackButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
    }
}

@Composable
fun MenuButton(onMenuClick: () -> Unit) {
    IconButton(onClick = onMenuClick) {
        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
    }
}

/**
 *- Has built in confirmation option
 * - Wrapper around to the [IconButtonWithConfirmation]
 */
@Composable
fun DeleteButton(
    modifier: Modifier=Modifier,
    onConfirm: () -> Unit,
) {
    IconButtonWithConfirmation(
        modifier = modifier,
        onConfirm = onConfirm,
        icon = Icons.Outlined.Delete,
        tint = MaterialTheme.colorScheme.secondary,
        message = "Are you sure you want to delete?"
    )

}

/**
 * Usage example
 * ```kotlin
 *
 * ```
 */

@Composable
fun IconButtonWithConfirmation(
    onConfirm: () -> Unit,
    icon: ImageVector,
    tint: Color,
    message: String,
    modifier: Modifier=Modifier
) {
    /**
     * - This just for confirmation, so it make sense  to not hoist the state
     */
    var showDialog by rememberSaveable { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onConfirm() // Perform the confirmed action
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            },
            text = { Text(message) }
        )
    }

    IconButton(modifier = modifier, onClick = { showDialog = true }) {
        Icon(imageVector = icon, contentDescription = null, tint = tint)
    }
}