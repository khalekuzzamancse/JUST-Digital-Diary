package calender.add_calender

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp

// Example usage in a parent composable
@Composable
fun ParentComposable(
    onDismiss: () -> Unit,
    onDone: (reason: String, selectedOption: Option) -> Unit
) {
    var reason by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<Option?>(null) }

    ReasonDialog(
        onDismiss = onDismiss,
        onDone =onDone
    )

}

// Enum class for the radio button options
enum class Option {
    AllOff,
    OnlyClassOf,
    SpecialDay
}

// The main dialog composable function
@Composable
fun ReasonDialog(
    onDismiss: () -> Unit,
    onDone: (reason: String, selectedOption: Option) -> Unit
) {
    // State variables for reason and selected option
    var reason by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<Option?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Enter Reason") },
        text = {
            Column {
                ReasonTextField(reason) { reason = it }
                Spacer(modifier = Modifier.height(16.dp))
                OptionRadioButtons(selectedOption) { selectedOption = it }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDone(reason, selectedOption!!)
                },
                enabled = reason.isNotEmpty() && selectedOption != null
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

// Composable function for the reason input field
@Composable
fun ReasonTextField(reason: String, onReasonChange: (String) -> Unit) {
    OutlinedTextField(
        value = reason,
        onValueChange = onReasonChange,
        label = { Text("Reason") },
        modifier = Modifier.fillMaxWidth()
    )
}

// Composable function for the radio buttons
@Composable
fun OptionRadioButtons(selectedOption: Option?, onOptionSelected: (Option) -> Unit) {
    Column {
        Option.values().forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOptionSelected(option) }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selectedOption == option,
                    onClick = { onOptionSelected(option) }
                )
                Text(text = option.name, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
