package miscellaneous.ui.home.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import miscellaneous.ui.home.calender.CalenderUI

@Composable
fun HomeDestination(
    onEvent: (HomeDestinationEvent) -> Unit,
) {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var snackBarJob: Job? = remember { null } // Track the current SnackBar Job

    Scaffold(
        modifier = Modifier,
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(hostState = hostState) },
        floatingActionButton = {
            CalenderDownloadButton {
                onEvent(
                    HomeDestinationEvent
                        .CalenderViewRequest("https://drive.google.com/file/d/1Ar0xjbA6H_rMAPrBDPzjjqqW1ainIOmU/view?usp=sharing")
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeHeader(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                onAdminOfficeRequest = {
                    onEvent(HomeDestinationEvent.NavigateTAdminOfficeList)
                },
                onFacultyListRequest = {
                    onEvent(HomeDestinationEvent.NavigateToFacultyList)
                }
            )
            CalenderUI(
                onSnackBarMsgRequest = { msg ->
                    snackBarJob?.cancel() // Cancel the previous SnackBar job if it's still active
                    snackBarJob =
                        scope.launch { // Launch a new coroutine to show the latest message
                            hostState.showSnackbar(msg, duration = SnackbarDuration.Short)
                        }
                }
            )

        }
    }


}

@Composable
private fun CalenderDownloadButton(
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        ) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.secondary)
            )
        }
    }

}