package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.common_ui.progressbar.ProgressBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.ElevatedField
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreenCom(
    onExitRequest: () -> Unit,
    onRegisterSuccess: () -> Unit = {},

    ) {
    val scope = rememberCoroutineScope()
    val viewModel = remember { RegistrationViewModel(scope) }
    val showProgressBar = viewModel.showProcessBar.collectAsState().value
    val showToast = viewModel.screenMessage.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    if (showToast != null) {
        scope.launch {
            snackbarHostState.showSnackbar(showToast)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }, topBar = {
            RegistrationTopAppBar(
                title = "Registration Form",
                onNavigationIconClick = onExitRequest
            )
        }
    ) {
        ProgressBarDecorator(
            modifier = Modifier.padding(it).fillMaxSize(),
            showProgressBar = showProgressBar,
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