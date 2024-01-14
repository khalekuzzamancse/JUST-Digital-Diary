package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch




@Composable
fun LoginSection(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onNavigateToRegisterScreen: () -> Unit = {},
    onLoginSuccess: () -> Unit = {},
    onPasswordResetRequest: () -> Unit = {},
) {

    val scope = rememberCoroutineScope()
    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHeaderSection()
        Spacer(Modifier.height(16.dp))
        LoginFieldsNControls(
            modifier = Modifier,
            isHorizontal = false,
            onRegisterRequest = onNavigateToRegisterScreen,
            onLoginRequest = {
                scope.launch {
                    val success = viewModel.onLoginRequest()
                    if (success) {
                        onLoginSuccess()
                    }
                }
            },
            onPasswordResetRequest = onPasswordResetRequest,
            state = viewModel
        )

    }

}

@Composable
fun LoginFieldsNControls(
    modifier: Modifier,
    state: LoginViewModel,
    isHorizontal: Boolean,
    onRegisterRequest: () -> Unit,
    onPasswordResetRequest: () -> Unit,
    onLoginRequest: () -> Unit,
) {

    Surface(
        modifier = modifier.padding(16.dp).background(Color.Red),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            LoginForm(
                modifier = Modifier.widthIn(max = 500.dp),
                state = state
            )
            Spacer(Modifier.height(16.dp))
            ForgetPassword(
                modifier = Modifier.align(Alignment.End),
                onPasswordResetRequest = onPasswordResetRequest
            )
            VerticalSpacer()
            LoginOrSignUp(
                modifier = if (isHorizontal)
                    Modifier.padding(start = 16.dp)
                else Modifier.padding(start = 16.dp),
                onRegisterRequest = onRegisterRequest,
                onLoginRequest = onLoginRequest
            )

        }
    }


}


//
@Composable
private fun LoginOrSignUp(
    modifier: Modifier,
    onRegisterRequest: () -> Unit,
    onLoginRequest: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max), // add this modifier
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.width(16.dp))
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't Have an account ?")
            Spacer(Modifier.width(4.dp))
            TextButton(onClick = onRegisterRequest) {
                Text(
                    text = "Register"
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginRequest
        ) {
            Text(text = "Login".uppercase())
        }

    }


}

@Composable
private fun ForgetPassword(
    modifier: Modifier,
    onPasswordResetRequest: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = onPasswordResetRequest) {
            Text(
                text = "Forget Password ?",
            )
        }
    }
}


@Composable
fun VerticalSpacer() {
    Spacer(
        modifier = Modifier
            .height(8.dp)

    )
}
