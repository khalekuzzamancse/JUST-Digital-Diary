package common.newui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

@Composable
fun SnackNProgressBarDecorator(
    snackBarData: CustomSnackBarData? = null,
    showProgressBar: Boolean = false,
    onSnackBarCloseRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Box {
        content()
        //show snack-bar on top of screen
        if (snackBarData != null) {
            SnackBarDecorator(
                data = snackBarData,
                onSnackBarCloseRequest = onSnackBarCloseRequest
            )
        }
        //show progress-bar on top of screen
        if (showProgressBar) {
            ProgressBarDecorator()
        }
    }

}