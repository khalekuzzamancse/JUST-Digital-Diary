package common.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.progressbar.ProgressBarNSnackBarDecorator
import common.ui.snackbar.SnackBarData

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun WindowSizeDecorator(
    snackBarMessage: String? = null,
    showProgressBar: Boolean = false,
    onCompact: @Composable () -> Unit,
    onMedium: @Composable () -> Unit,
    onExpanded: @Composable () -> Unit) {
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
                WindowWidthSizeClass.Medium -> {
                    onMedium()
                }
                WindowWidthSizeClass.Expanded -> {
                    onExpanded()

                }
            }

        }
    }



}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun WindowSizeDecorator(
    modifier: Modifier=Modifier,
    snackBarMessage: String? = null,
    snackBarData: SnackBarData?=null,
    showProgressBar: Boolean = false,
    onNonExpanded: @Composable () -> Unit,
    onExpanded: @Composable () -> Unit) {
    val windowSize = calculateWindowSizeClass()
    val windowWidth = windowSize.widthSizeClass

    ProgressBarNSnackBarDecorator(
        modifier = modifier.fillMaxSize(),
        showProgressBar = showProgressBar,
        snackBarData = snackBarData,
        snackBarMessage = snackBarMessage
    ) {
        when (windowWidth) {
            WindowWidthSizeClass.Compact , WindowWidthSizeClass.Medium-> {
                onNonExpanded()
            }
            WindowWidthSizeClass.Expanded-> {
                onExpanded()
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
    when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                onCompact()
            }

            WindowWidthSizeClass.Expanded, WindowWidthSizeClass.Medium -> {
                onNonCompact()
            }
        }



}