package common.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Update
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
/**
 * - Hide the soft keyboard automatically
 */
@Composable
fun UpdateButton(
    modifier: Modifier =  Modifier.widthIn(min = 100.dp, max = 300.dp).fillMaxWidth(),
    enabled: Boolean =true,
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
