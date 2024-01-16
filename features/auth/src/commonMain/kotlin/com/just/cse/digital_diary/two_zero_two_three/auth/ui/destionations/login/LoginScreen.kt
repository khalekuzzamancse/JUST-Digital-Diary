package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTopBar
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginControls
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginForm
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.header.LoginHeaderSection

@Composable
fun LoginScreenCom(
    onLoginSuccess: () -> Unit ,
    onNavigateToRegisterScreen: () -> Unit = {}
) {
    val viewModel = remember { LoginViewModel(
        onLoginSuccess=onLoginSuccess
    ) }
    WindowSizeDecorator(
        showProgressBar = viewModel.showProcessBar.collectAsState().value,
        snackBarMessage = viewModel.screenMessage.collectAsState().value,
        onCompact = {
            Scaffold(
                topBar = {
                    AuthTopBar(
                        title = "Login",
                        onNavigationIconClick = {},
                        navigationIcon = null
                    )
                }
            ) {
                LoginSlot(
                    modifier = Modifier.padding(it).fillMaxWidth(),
                    viewModel = viewModel,
                    onNavigateToRegisterScreen = onNavigateToRegisterScreen
                )
            }
        },
        onNonCompact = {
            Box(modifier = Modifier.fillMaxSize()) {
                LoginSlot(
                    modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
                    viewModel = viewModel,
                    onNavigateToRegisterScreen = onNavigateToRegisterScreen
                )
            }

        }
    )


}

@Composable
private fun LoginSlot(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onNavigateToRegisterScreen: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHeaderSection()
        Surface(
            modifier = Modifier.padding(8.dp),
            shadowElevation = 6.dp,
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Box(Modifier.widthIn(max = 500.dp).align(Alignment.CenterHorizontally)) {
                    LoginForm(
                        data = viewModel.data.collectAsState().value,
                        fieldModifier = Modifier.fillMaxWidth(),
                        onUserNameChanged = viewModel::onUserNameChanged,
                        onPasswordChanged = viewModel::onPasswordChanged,
                    )
                }

                Spacer(Modifier.height(16.dp))
                LoginControls(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onRegisterRequest = onNavigateToRegisterScreen,
                    onLoginRequest = viewModel::onLoginRequest,
                    onPasswordResetRequest = { },
                )

            }
        }
    }
}
