package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun WindowSizeDecorator(
    snackBarMessage: String? = null,
    showProgressBar: Boolean = false,
    onCompact: @Composable () -> Unit,
    onNonCompact: @Composable () -> Unit,

    ) {
    val windowSize = calculateWindowSizeClass()
    val compactScreen = windowSize.widthSizeClass
    ProgressBarNSnackBarDecorator(
        modifier = Modifier.fillMaxSize(),
        showProgressBar = showProgressBar,
        snackBarMessage = snackBarMessage
    ) {
        AnimatedContent(
            targetState = compactScreen
        ) { width ->
            when (width) {
                WindowWidthSizeClass.Compact -> {
                    onCompact()
                }

                WindowWidthSizeClass.Expanded, WindowWidthSizeClass.Medium -> {

                    onNonCompact()

                }
            }

        }
    }


}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun WindowSizeDecorator(
    onCompact: @Composable () -> Unit,
    onNonCompact: @Composable () -> Unit,
) {
    val windowSize = calculateWindowSizeClass()
    val compactScreen = windowSize.widthSizeClass
    AnimatedContent(
        targetState = compactScreen
    ) { width ->
        when (width) {
            WindowWidthSizeClass.Compact -> {
                onCompact()
            }

            WindowWidthSizeClass.Expanded, WindowWidthSizeClass.Medium -> {
                onNonCompact()
            }
        }

    }

}