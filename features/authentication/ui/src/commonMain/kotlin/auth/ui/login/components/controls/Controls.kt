package auth.ui.login.components.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import auth.ui.login.components.event.LoginEvent

@Composable
internal fun LoginControls(
    modifier: Modifier,
    onEvent: (LoginEvent.LoginControlsEvent) -> Unit,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ForgetPassword(
            modifier = Modifier.align(Alignment.End),
            onPasswordResetRequest = {
                onEvent(LoginEvent.LoginControlsEvent.PasswordResetRequest)
            }
        )
        VerticalSpacer()
        LoginOrSignUp(
            modifier = Modifier.padding(start = 16.dp),
            onRegisterRequest = {
                onEvent(LoginEvent.LoginControlsEvent.RegisterRequest)
            },
            onLoginRequest = {
                onEvent(LoginEvent.LoginControlsEvent.LoginRequest)
            }
        )

    }


}


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
private fun VerticalSpacer() {
    Spacer(
        modifier = Modifier
            .height(8.dp)

    )
}
