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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.presentationlogic.controller.RegisterController
import auth.ui.common.AuthPasswordField
import common.ui.CustomTextField
import common.ui.ErrorText
import kotlinx.coroutines.launch


@Composable
internal fun RegisterDestination(
    modifier: Modifier = Modifier,
    controller: RegisterController,
    onLoginRequest: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    //Simple state it is okay to define here,need to hoist
    var showOTPDialog by rememberSaveable { mutableStateOf(false) }
    if (showOTPDialog) {
        _OTPDialog(
            onDismiss = { showOTPDialog = false },
            onDone = { username, otp ->
               keyboardController?.hide()
                showOTPDialog = false
                scope.launch {
                    controller.verifyAccount(username, otp)
                }
            }
        )

    }


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
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .padding(
                        vertical = 32.dp,
                        horizontal = 16.dp
                    ).verticalScroll(
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
                        keyboardController?.hide()
                        scope.launch {
                            controller.register()
                        }
                    },
                    onOTPVerifyRequest = {
                        showOTPDialog = true
                    }
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
    onOTPVerifyRequest: () -> Unit,
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
            _LoginAndOTP(
                enabled = enableControls,
                modifier = Modifier,
                onLoginRequest = onNavigateToLoginRequest,
                onOTPVerifyRequest = onOTPVerifyRequest
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
            enabled = enableControls && noError && allFieldsFilled
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
            onValueChange = onNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Outlined.Person,
        )
        Spacer(Modifier.height(8.dp))

        CustomTextField(
            modifier = fieldModifier,
            label = "Email",
            value = email,
            onValueChange = onEmailChanged,
            keyboardType = KeyboardType.Email,
            leadingIcon = Icons.Outlined.Email,
        )
        Spacer(Modifier.height(8.dp))

        CustomTextField(
            modifier = fieldModifier,
            label = "Username",
            value = username,
            onValueChange = onUserNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Outlined.Person2,
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
private fun _LoginAndOTP(
    modifier: Modifier,
    enabled: Boolean,
    onLoginRequest: () -> Unit,
    onOTPVerifyRequest: () -> Unit,
) {
    Column {
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
                    text = "Login",
                    color =if (enabled) MaterialTheme.colorScheme.secondary else Color.Unspecified
                )
            }
        }
        FlowRow(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Already Registered ?",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(Modifier.width(4.dp))
            TextButton(
                onClick = onOTPVerifyRequest,
                enabled = enabled,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Verify",
                    color =if (enabled) MaterialTheme.colorScheme.tertiary else Color.Unspecified
                )
            }
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

@Composable
private fun _OTPDialog(
    onDismiss: () -> Unit,
    onDone: (username: String, otp: String) -> Unit
) {
    // Keep the OTP state within the dialog
    var otp by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        text = {
            Column {
                CustomTextField(
                    value = username,
                    onValueChange = { input ->
                        username = input
                    },
                    label = "Identifier",
                    leadingIcon = Icons.Outlined.Email,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(8.dp))

                CustomTextField(
                    value = otp,
                    onValueChange = { input ->
                        otp = input
                    },
                    label = "OTP",
                    leadingIcon = Icons.Outlined.Security,
                    modifier = Modifier.fillMaxWidth(),
                )

            }
        },
        confirmButton = {
            TextButton(
                onClick = { onDone(username, otp) },
                enabled = otp.length >= 3 && username.length >= 3
            ) {
                Text(text = "Verify")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel")
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}

