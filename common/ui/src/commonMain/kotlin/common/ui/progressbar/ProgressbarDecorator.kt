package common.ui.progressbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import common.ui.snackbar.CustomSnackBar
import common.ui.snackbar.SnackBarData


@Composable
fun ProgressBarNSnackBarDecorator(
    modifier: Modifier = Modifier,
    snackBarMessage: String? = null,
    snackBarData: SnackBarData?=null,
    showProgressBar: Boolean = false,
    content: @Composable () -> Unit,
) {

    if (snackBarMessage != null) {
        SnackBar(
            message = snackBarMessage,
            snackBarData=snackBarData,
            content = content
        )
    } else {
        Box(
            modifier = modifier,
        ) {
            content()
            AnimatedVisibility(
                visible = showProgressBar
            ) {
                ProgressBar()
            }
        }
    }


}

@Composable
private fun SnackBar(
    message: String,
    snackBarData: SnackBarData?=null,
    content: @Composable () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        snackBarHostState.showSnackbar(message)
    }
    AnimatedVisibility(
        visible = snackBarData != null
    ) {
        Scaffold(
            modifier = Modifier,
            snackbarHost = {
                if (snackBarData != null) {
                    CustomSnackBar(snackBarData)
                }
                // SnackbarHost(hostState = snackBarHostState)
            }
        ) {
            Box(Modifier.padding(it)) {
                content()
            }

        }
    }

}


@Composable
private fun ProgressBar() {
    Box(Modifier.fillMaxSize().background(Color.Gray.copy(alpha = 0.7f))) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}