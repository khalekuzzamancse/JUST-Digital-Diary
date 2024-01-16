package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTopBar
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreenCom(
    onExitRequest: () -> Unit,
    onRegisterSuccess: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val viewModel = remember { RegistraionViewModel(scope) }

    ProgressBarNSnackBarDecorator(
        modifier = Modifier.fillMaxSize(),
        showProgressBar =  viewModel.showProcessBar.collectAsState().value,
        snackBarMessage = viewModel.screenMessage.collectAsState().value
    ) {
    Scaffold(
        topBar = {
            AuthTopBar(
                title = "Registration Form",
                onNavigationIconClick = onExitRequest
            )
        }
    ) {

            RegisterSection(
                viewModel = viewModel,
                onRegisterSuccess = {
                    scope.launch {
                        delay(1000)
                        onRegisterSuccess()
                    }
                }
            )

        }
    }


}