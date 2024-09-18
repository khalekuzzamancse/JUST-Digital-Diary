package miscellaneous.ui.home.home

import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender.AcademicCalender
import common.ui.progressbar.ProgressBarNSnackBarDecorator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeDestination(
    onEvent: (HomeDestinationEvent) -> Unit,
) {
    val uiState = remember { HomeDestinationState() }
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val windowSize = calculateWindowSizeClass().widthSizeClass
    val compact = WindowWidthSizeClass.Compact
    val medium = WindowWidthSizeClass.Medium
    val expanded = WindowWidthSizeClass.Expanded
    var varsityGateSize = animateDpAsState(100.dp)
    var varsityLogoSize = animateDpAsState(50.dp)

    Scaffold(
        modifier = Modifier,
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(hostState = hostState) },
        floatingActionButton = {
            CalenderDownloadButton {
                onEvent(
                    HomeDestinationEvent.CalenderViewRequest("https://drive.google.com/file/d/1Ar0xjbA6H_rMAPrBDPzjjqqW1ainIOmU/view?usp=sharing")
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeToHome(modifier = Modifier.padding(16.dp).fillMaxWidth())
            AcademicCalender(
                modifier = Modifier.padding(8.dp),
                monthBuilder = uiState.calenderBuilder.collectAsState().value,
                currentYear = uiState.currentYear,
                currentMonth = uiState.monthName,
                onNext = uiState::goToNextMonth,
                onPrev = uiState::getPreviousMonth,
                onDayClick = {
                    scope.launch {
                        hostState.showSnackbar(
                            message = "Friday",
                        )
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