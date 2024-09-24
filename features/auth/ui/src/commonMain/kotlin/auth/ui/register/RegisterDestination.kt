package auth.ui.register

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
internal fun RegisterDestination(
    modifier: Modifier = Modifier,
    onLoginRequest: () -> Unit,
) {
    val viewModel = remember { RegisterDestinationViewModel(UiFactory.createRegisterController()) }
    val controller = viewModel.controller
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        RegisterFormNControls(
            modifier = modifier.widthIn(max = 700.dp).fillMaxWidth(),
            controller = controller,
            onNavigateToLoginRequest = onLoginRequest,
            onRegisterRequest = {
            },
        )
    }

}


@Composable
internal fun RegisterFormNControls(
    modifier: Modifier,
    controller: RegisterController,
    onRegisterRequest: () -> Unit,
    onNavigateToLoginRequest: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {

        RegistrationForm(
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
            LoginSection(
                modifier = Modifier,
                onLoginRequest = onNavigateToLoginRequest
            )
        }
        RegistrationControls(
            modifier = Modifier.widthIn(min = 200.dp, max = 300.dp)
                .align(Alignment.CenterHorizontally),
            onRegistrationRequest = onRegisterRequest
        )

    }
}

@Composable
private fun RegistrationForm(
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


        CustomTextField(
            modifier = fieldModifier,
            label = "Email",
            value = email,
            onValueChanged = onEmailChanged,
            keyboardType = KeyboardType.Email,
            leadingIcon = Icons.Default.Email,
        )


        CustomTextField(
            modifier = fieldModifier,
            label = "Username",
            value = username,
            onValueChanged = onUserNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Default.Person2,
        )


        AuthPasswordField(
            modifier = fieldModifier,
            label = "Password",
            value = password,
            onValueChanged = onPasswordChanged,
        )

        AuthPasswordField(
            modifier = fieldModifier,
            label = "Confirm password",
            value = confirmedPassword,
            onValueChanged = onConfirmedPassword,
        )


    }

}


@Composable
internal fun RegistrationControls(
    modifier: Modifier,
    onRegistrationRequest: () -> Unit,
) {
    Button(
        modifier = modifier,
        elevation = ButtonDefaults
            .buttonElevation(defaultElevation = 8.dp, focusedElevation = 8.dp),
        onClick = onRegistrationRequest
    ) {
        Text(text = "Register".uppercase())
    }
}

@Composable
private fun LoginSection(
    modifier: Modifier,
    onLoginRequest: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max), // add this modifier
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already Have an account ?")
            Spacer(Modifier.width(4.dp))
            TextButton(onClick = onLoginRequest) {
                Text(
                    text = "Login"
                )
            }
        }

    }


}

@Composable
private fun RegistrationHeaderSection(
    modifier: Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(bottomStart = 80.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        ) {
            ImageLoader(
                url = "https://automation.just.edu.bd/images/just-logo.png",
                modifier = Modifier.height(60.dp).align(Alignment.Center)
            )

        }
    }
}