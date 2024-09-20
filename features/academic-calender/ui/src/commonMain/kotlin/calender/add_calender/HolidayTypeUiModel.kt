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
    onDone: (reason: String, selectedOption: HolidayTypeUiModel) -> Unit
) {
    var reason by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<HolidayTypeUiModel?>(null) }

    ReasonDialog(
        onDismiss = onDismiss,
        onDone = onDone
    )

}

/**
 * @param color color hex code
 */
enum class HolidayTypeUiModel(val color:String) {
    AllOff("#FF0000"),    // Red color hex code for AllOff
    OnlyClassOf("#00FF00"), // Green color hex code for OnlyClassOff
    SpecialDay("#800080")//Purple color hex code for SpecialDay
}

// The main dialog composable function
@Composable
fun ReasonDialog(
    onDismiss: () -> Unit,
    onDone: (reason: String, selectedOption: HolidayTypeUiModel) -> Unit
) {
    // State variables for reason and selected option
    var reason by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<HolidayTypeUiModel?>(null) }

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
fun OptionRadioButtons(selectedOption: HolidayTypeUiModel?, onOptionSelected: (HolidayTypeUiModel) -> Unit) {
    Column {
        HolidayTypeUiModel.values().forEach { option ->
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
