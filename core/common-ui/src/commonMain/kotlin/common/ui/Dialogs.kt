package common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ContactSelectionDialog(
    contactString: String, // The input string (comma-separated)
    onDismissRequest: () -> Unit, // Action for dismissing the dialog
    onItemSelected: (String) -> Unit // Lambda to propagate the selected item
) {
    // Parse the input string
    val contacts = remember { parseContactString(contactString) }

    // If there is only one contact, propagate the selection and skip the dialog
    if (contacts.size == 1) {
        LaunchedEffect(contacts.first()) {
            onItemSelected(contacts.first())
        }
        return
    }

    // Display the dialog for multiple contacts
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {},
        title = { Text(text = "Select Contact") },
        text = {
            Column {
                contacts.forEach { contact ->
                    ContactOption(contact = contact, onClick = {
                        onItemSelected(contact) // Propagate selected item to the parent
                        onDismissRequest() // Dismiss dialog after selection
                    })
                }
            }
        }
    )
}

// Function to split the input string by commas and return a list of trimmed contacts
private fun parseContactString(contactString: String): List<String> {
    return contactString.split(",").map { it.trim() }.filter { it.isNotEmpty() }
}

@Composable
private fun ContactOption(contact: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Padding around the contact item
            .clickable(onClick = onClick), // Add clickable with ripple effect
        color = MaterialTheme.colorScheme.surface, // Optional background color
        shape = MaterialTheme.shapes.small, // Optional rounded shape
        shadowElevation = 4.dp // Optional elevation for shadow effect
    ) {
        Text(
            text = contact,
            modifier = Modifier
                .padding(16.dp), // Internal padding for the text
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary, // Make text color attractive
                textDecoration = TextDecoration.Underline // Underline to signify it's clickable
            )
        )
    }
}
