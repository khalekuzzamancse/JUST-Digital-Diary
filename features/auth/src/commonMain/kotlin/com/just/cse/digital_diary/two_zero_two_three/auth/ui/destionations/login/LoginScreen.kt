package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreenCom(
    onLoginSuccess: () -> Unit = {},
    onNavigateToRegisterScreen: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val viewModel = remember { LoginViewModel(scope) }
    ProgressBarNSnackBarDecorator(
        modifier = Modifier.fillMaxSize(),
        showProgressBar = viewModel.showProcessBar.collectAsState().value,
        snackBarMessage = viewModel.screenMessage.collectAsState().value
    ) {
    Scaffold(
        topBar = {
            AuthTopBar(
                title = "Login",
                onNavigationIconClick = {},
                navigationIcon = null
            )
        }
    ) {
            LoginSection(
                modifier = Modifier.padding(it),
                viewModel = viewModel,
                onNavigateToRegisterScreen = onNavigateToRegisterScreen,
                onLoginSuccess = {
                    scope.launch {
                        delay(1000) //delay show to success message
                        onLoginSuccess()
                    }

                },
                onPasswordResetRequest = {}
            )
        }
    }


}

