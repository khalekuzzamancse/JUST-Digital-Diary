package just.digitaldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import just.digitaldiary.theme.AppTheme
import navigation.AppEvent
import navigation.RootNavHost
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventHandler = AppEventHandler(this)

        setContent {
            AppTheme {

                var showDialog by remember { mutableStateOf(false) }
                var contactString by remember { mutableStateOf("") }
                var currentEvent: AppEvent? by remember { mutableStateOf(null) }

                // RootNavHost receives events and processes them
                RootNavHost(
                    onEvent = { event ->
                        when (event) {
                            is AppEvent.CallRequest -> {
                                // If there's a number or multiple numbers, show dialog
                                contactString = event.number
                                currentEvent = event
                                showDialog = true
                            }

                            is AppEvent.MessageRequest -> {
                                // If there's a number or multiple numbers, show dialog
                                contactString = event.number
                                currentEvent = event
                                showDialog = true
                            }

                            is AppEvent.EmailRequest -> {
                                // If there's an email or multiple emails, show dialog
                                contactString = event.email
                                currentEvent = event
                                showDialog = true
                            }

                            else -> {
                                // Handle other events directly (such as WebVisitRequest)
                                eventHandler.handleEvent(event)
                            }
                        }
                    }
                )

                // Show the contact selection dialog if necessary
                if (showDialog) {
                    ContactSelectionDialog(
                        contactString = contactString,
                        onDismissRequest = { showDialog = false },
                        onItemSelected = { selectedItem ->
                            // Modify the current event with the selected item and pass it to the handler
                            currentEvent?.let { event ->
                                when (event) {
                                    is AppEvent.CallRequest -> {
                                        eventHandler.handleEvent(event.copy(number = selectedItem))
                                    }
                                    is AppEvent.MessageRequest -> {
                                        eventHandler.handleEvent(event.copy(number = selectedItem))
                                    }
                                    is AppEvent.EmailRequest -> {
                                        eventHandler.handleEvent(event.copy(email = selectedItem))
                                    }
                                }
                            }
                            showDialog = false // Close the dialog after selection
                        }
                    )
                }
            }
        }
    }
}

