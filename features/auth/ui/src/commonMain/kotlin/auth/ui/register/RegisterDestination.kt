package auth.ui.register

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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.controller_presenter.controller.RegisterController
import auth.ui.common.AuthPasswordField
import common.newui.CustomTextField
import common.newui.ErrorText
import kotlinx.coroutines.launch


@Composable
internal fun RegisterDestination(
    modifier: Modifier = Modifier,
    controller: RegisterController,
    onLoginRequest: () -> Unit,
) {
    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier.padding(32.dp).fillMaxWidth().fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier,
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 2.dp,
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp).verticalScroll(
                    rememberScrollState()
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                _HeaderSection(modifier = Modifier)
                Spacer(Modifier.height(32.dp))
                RegisterFormNControls(
                    modifier = Modifier,
                    controller = controller,
                    onNavigateToLoginRequest = onLoginRequest,
                    onRegisterRequest = {
                        scope.launch {
                            controller.register()
                        }
                    },
                )
            }

        }

    }


}


@Composable
internal fun RegisterFormNControls(
    modifier: Modifier,
    controller: RegisterController,
    onRegisterRequest: () -> Unit,
    onNavigateToLoginRequest: () -> Unit,
) {
    val enableControls = !(controller.isRegistering.collectAsState()).value
    val noError = controller.validator.errors.collectAsState().value.isEmpty()
    val allFieldsFilled = controller.validator.areAllFieldsFilled.collectAsState().value
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        _Form(
            modifier = modifier,
            fieldModifier = Modifier,
            name = controller.state.collectAsState().value.name,
            onNameChanged = controller::onNameChanged,
            email = controller.state.collectAsState().value.email,
            onEmailChanged = controller::onEmailChanged,
            username = controller.state.collectAsState().value.username,
            onUserNameChanged = controller::onUsernameChanged,
            password = controller.state.collectAsState().value.password,
            onPasswordChanged = controller::onPasswordChanged,
            confirmedPassword = controller.state.collectAsState().value.confirmPassword,
            onConfirmedPassword = controller::onConfirmPasswordChanged
        )

        Spacer(Modifier.height(24.dp))
        Column(Modifier.padding(start = 16.dp).align(Alignment.CenterHorizontally)) {
            _LoginSection(
                enabled = enableControls,
                modifier = Modifier,
                onLoginRequest = onNavigateToLoginRequest
            )
        }
        controller.validator.errors.collectAsState().value.let { error ->
            if (error.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                ErrorText(modifier = Modifier, error)
            }
        }
        _Controls(
            modifier = Modifier
                .widthIn(min = 200.dp, max = 300.dp)
                .align(Alignment.CenterHorizontally),
            onRegistrationRequest = onRegisterRequest,
            enabled = enableControls&&noError&&allFieldsFilled
        )

    }
}

@Composable
private fun _Form(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    name: String,
    onNameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    username: String,
    onUserNameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    confirmedPassword: String,
    onConfirmedPassword: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        CustomTextField(
            modifier = fieldModifier,
            label = "Name",
            value = name,
            onValueChanged = onNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Default.Person,
        )
        Spacer(Modifier.height(8.dp))

        CustomTextField(
            modifier = fieldModifier,
            label = "Email",
            value = email,
            onValueChanged = onEmailChanged,
            keyboardType = KeyboardType.Email,
            leadingIcon = Icons.Default.Email,
        )
        Spacer(Modifier.height(8.dp))

        CustomTextField(
            modifier = fieldModifier,
            label = "Username",
            value = username,
            onValueChanged = onUserNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Default.Person2,
        )
        Spacer(Modifier.height(8.dp))

        AuthPasswordField(
            modifier = fieldModifier,
            label = "Password",
            value = password,
            onValueChanged = onPasswordChanged,
        )
        Spacer(Modifier.height(8.dp))
        AuthPasswordField(
            modifier = fieldModifier,
            label = "Confirm password",
            value = confirmedPassword,
            onValueChanged = onConfirmedPassword,
        )


    }

}


@Composable
private fun _Controls(
    modifier: Modifier,
    enabled: Boolean,
    onRegistrationRequest: () -> Unit,
) {
    Button(
        enabled = enabled,
        modifier = modifier,
        elevation = ButtonDefaults
            .buttonElevation(defaultElevation = 8.dp, focusedElevation = 8.dp),
        onClick = onRegistrationRequest
    ) {
        Text(text = "Register".uppercase())
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun _LoginSection(
    modifier: Modifier,
    enabled: Boolean,
    onLoginRequest: () -> Unit,
) {
    FlowRow(
        modifier = Modifier.width(IntrinsicSize.Max),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Have an account ?",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(Modifier.width(4.dp))
        TextButton(
            onClick = onLoginRequest,
            enabled = enabled,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Login"
            )
        }
    }


}

@Composable
private fun _HeaderSection(
    modifier: Modifier,
) {
    Text(
        modifier = modifier,
        text = "Register",
        style = MaterialTheme.typography.headlineMedium
    )
}