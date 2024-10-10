package auth.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.presentationlogic.controller.LoginController
import auth.ui.common.AuthPasswordField
import common.ui.CustomTextField
import kotlinx.coroutines.launch

/**
 * @param onLoginSuccess notify the parent so that parent can exist it
 */
@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    controller: LoginController,
    navigateToRegisterRequest: () -> Unit,
    onLoginSuccess: (token: String) -> Unit,
    onPasswordResetRequest: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxSize(), contentAlignment = Alignment.Center) {
        LoginFormNControls(
            modifier = Modifier.padding(32.dp),
            controller = controller,
            onLoginRequest = {
                coroutineScope.launch {
                    keyboardController?.hide()
                    controller.performLogin()?.let(onLoginSuccess)
                }

            },
            onRegisterRequest = navigateToRegisterRequest,
            onPasswordResetRequest = onPasswordResetRequest
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
    val isNotLoading = !(controller.isLogging.collectAsState()).value
    val noError = controller.validator.errors.collectAsState().value.isEmpty()
    val allFieldsFilled = controller.validator.areAllFieldsFilled.collectAsState().value

    Column(
        modifier = modifier
            .widthIn(max = 500.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier,
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 2.dp,
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                _LoginHeaderSection()
                Spacer(Modifier.height(32.dp))
                _LoginForm(
                    fieldModifier = Modifier.fillMaxWidth(),
                    userName = controller.state.collectAsState().value.username,
                    password = controller.state.collectAsState().value.password,
                    onUserNameChanged = controller::onUserNameChanged,
                    onPasswordChanged = controller::onPasswordChanged
                )
                Spacer(Modifier.height(16.dp))
                _ForgetPassword(
                    modifier = Modifier.align(Alignment.End),
                    onPasswordResetRequest = onPasswordResetRequest,
                    enabled = isNotLoading
                )
                Spacer(Modifier.height(16.dp))
                LoginControls(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onLoginRequest = onLoginRequest,
                    onRegisterRequest = onRegisterRequest,
                    enableLogin = isNotLoading && allFieldsFilled && noError,
                    enableRegister = isNotLoading
                )

            }
        }
    }
}


@Composable
private fun _LoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    userName: String,
    password: String,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
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
            leadingIcon = Icons.Outlined.Person,
        )
        Spacer(Modifier.height(8.dp))
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
    enableRegister: Boolean,
    enableLogin: Boolean,
    onLoginRequest: () -> Unit,
    onRegisterRequest: () -> Unit,
) {

    LoginOrSignUp(
        modifier = Modifier.padding(start = 16.dp),
        onRegisterRequest = onRegisterRequest,
        onLoginRequest = onLoginRequest,
        enableLogin = enableLogin,
        enableRegister = enableRegister
    )


}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LoginOrSignUp(
    modifier: Modifier,
    enableRegister: Boolean,
    enableLogin: Boolean,
    onRegisterRequest: () -> Unit,
    onLoginRequest: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max), // add this modifier
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Don't Have account ?",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(Modifier.width(4.dp))
            TextButton(
                enabled = enableRegister,
                onClick = onRegisterRequest,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Register",
                    color = if (enableRegister) MaterialTheme.colorScheme.secondary else Color.Unspecified
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginRequest,
            enabled = enableLogin
        ) {
            Text(text = "Login".uppercase())
        }

    }


}

@Composable
private fun _ForgetPassword(
    modifier: Modifier,
    enabled: Boolean,
    onPasswordResetRequest: () -> Unit,
) {
    TextButton(
        onClick = onPasswordResetRequest,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(
            text = "Forget Password ?",
            color = if (enabled) MaterialTheme.colorScheme.tertiary else Color.Unspecified
        )
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
private fun _LoginHeaderSection(
    modifier: Modifier = Modifier
) {
    Text(text = "Login", style = MaterialTheme.typography.headlineMedium)
}