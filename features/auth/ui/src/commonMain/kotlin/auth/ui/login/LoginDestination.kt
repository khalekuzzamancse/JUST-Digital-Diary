package auth.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.common.AuthPasswordField
import auth.factory.UiFactory
import common.newui.CustomTextField
import common.ui.network_image.ImageLoader

@Composable
internal fun LoginDestination(
    modifier: Modifier = Modifier,
    onNavigateToRegisterRequest: () -> Unit
) {
    val viewModel= remember { LoginDestinationViewModel(UiFactory.createLoginController()) }
    val controller=viewModel.controller
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        LoginFormNControls(
            modifier = Modifier,
            controller =controller,
            onLoginRequest = {},
            onRegisterRequest = onNavigateToRegisterRequest,
            onPasswordResetRequest = {}
        )

    }

}

/**
 * A [Stateless Component]
 * For the Login destination.
 */
@Composable
internal fun LoginFormNControls(
    modifier: Modifier = Modifier,
    controller: LoginController,
    onLoginRequest: () -> Unit,
    onRegisterRequest: () -> Unit,
    onPasswordResetRequest: () -> Unit
) {
    Column(
        modifier = modifier.widthIn(max=700.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier =Modifier,
            shadowElevation = 6.dp,
        ) {
            Column(modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal =16.dp
            )) {
                Box(Modifier.align(Alignment.CenterHorizontally)) {
                    LoginForm(
                        fieldModifier = Modifier.fillMaxWidth(),
                        userName =controller.state.collectAsState().value.username,
                        password =controller.state.collectAsState().value.password,
                        onUserNameChanged =controller::onUserNameChanged,
                        onPasswordChanged =controller::onPasswordChanged
                    )
                }
                Spacer(Modifier.height(16.dp))
                LoginControls(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onLoginRequest = onLoginRequest,
                    onRegisterRequest = onRegisterRequest,
                    onPasswordResetRequest = onPasswordResetRequest
                )

            }
        }
    }
}


@Composable
internal fun LoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    userName: String,
    password: String,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {
    CompactScreenLoginForm(
        fieldModifier = fieldModifier,
        formModifier = formModifier,
        userName = userName,
        onUserNameChanged = onUserNameChanged,
        password = password,
        onPasswordChanged = onPasswordChanged
    )
}


@Composable
private fun CompactScreenLoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    userName: String,
    onUserNameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
) {
    Column(
        modifier = formModifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CustomTextField(
            modifier = fieldModifier,
            label = "User Name",
            value = userName,
            onValueChanged = onUserNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Default.Person,
        )

        AuthPasswordField(
            modifier = fieldModifier,
            label = "Password",
            value = password,
            onValueChanged = onPasswordChanged,
        )


    }


}



@Composable
internal fun LoginControls(
    modifier: Modifier,
    onLoginRequest: () -> Unit,
    onRegisterRequest: () -> Unit,
    onPasswordResetRequest: () -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ForgetPassword(
            modifier = Modifier.align(Alignment.End),
            onPasswordResetRequest = onPasswordResetRequest
        )
        VerticalSpacer()
        LoginOrSignUp(
            modifier = Modifier.padding(start = 16.dp),
            onRegisterRequest = onRegisterRequest,
            onLoginRequest = onLoginRequest
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


@Composable
internal fun LoginHeaderSection() {
    Surface(
        modifier = Modifier,
    ) {

        Column {
            ImageLoader(
                url = "https://just.edu.bd/logo/download.png",
                modifier = Modifier.height(100.dp),
                onSuccess = {
                }

            )
        }
    }
}