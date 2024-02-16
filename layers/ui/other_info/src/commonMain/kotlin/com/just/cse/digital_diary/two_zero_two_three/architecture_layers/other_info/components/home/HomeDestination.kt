package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender.AcademicCalender
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.other.WelcomeToHome
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import kotlinx.coroutines.launch


@Composable
fun HomeDestination(
    onEvent: (HomeDestinationEvent) -> Unit,
    universityLogo: @Composable () -> Unit = {},
    university: @Composable () -> Unit = {},
) {

    val uiState = remember { HomeDestinationState() }
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    ProgressBarNSnackBarDecorator { }
    Scaffold(
        modifier = Modifier,
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = {
            SnackbarHost(hostState = hostState)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(
                    HomeDestinationEvent
                        .CalenderViewRequest("https://drive.google.com/file/d/1Ar0xjbA6H_rMAPrBDPzjjqqW1ainIOmU/view?usp=sharing")
                )
            }) {
                Row(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeToHome(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                university = university,
                universityLogo = universityLogo
            )
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
                            message = "Because of Friday",
                        )
                    }

                }
            )

        }
    }


}


fun isColorDark(color: Color): Boolean {
    val luminance = 0.299 * color.red + 0.587 * color.green + 0.114 * color.blue
    return luminance < 0.5
}