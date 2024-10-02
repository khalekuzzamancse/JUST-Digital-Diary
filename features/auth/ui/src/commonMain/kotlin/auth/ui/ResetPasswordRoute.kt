package auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import auth.presentationlogic.controller.PasswordResetController
import auth.ui.common.AuthPasswordField
import common.ui.CustomTextField
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordRoute(
    controller: PasswordResetController,
    onExitRequest: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onExitRequest
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) {
        Box(
            Modifier.padding(it)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(8.dp).widthIn(max = 500.dp)

            ) {
                CustomTextField(
                    value = controller.email.collectAsState().value,
                    onValueChange = controller::onEmailChanged,
                    label = "Email",
                    leadingIcon = Icons.Outlined.Email,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(12.dp))
                _ResetForm(
                    modifier = Modifier,
                    controller = controller,
                )

                Spacer(Modifier.height(48.dp))
                _Controls(
                    modifier = Modifier,
                    controller = controller
                )

            }

        }
    }

}

@Composable
private fun _ResetForm(
    modifier: Modifier = Modifier,
    controller: PasswordResetController,
) {
    val enabled = controller.isSendResetRequestSuccessful.collectAsState().value
    CustomTextField(
        enabled = enabled,
        value = controller.code.collectAsState().value,
        onValueChange = controller::onCodeChanged,
        label = "Code",
        leadingIcon = Icons.Outlined.Security,
        modifier = Modifier.fillMaxWidth(),
    )
    Spacer(Modifier.height(12.dp))
    AuthPasswordField(
        modifier = Modifier,
        enabled = enabled,
        label = "New Password",
        value = controller.newPassword.collectAsState().value,
        onValueChanged = controller::onPasswordChanged,
    )

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun _Controls(
    modifier: Modifier = Modifier,
    controller: PasswordResetController
) {
    val enabled = controller.isSendResetRequestSuccessful.collectAsState().value
    val scope = rememberCoroutineScope()
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                scope.launch {
                    controller.sendResetRequest()
                }
            },
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = "Send Request",
            )
        }

        Button(
            onClick = {
                scope.launch {
                    controller.resetPassword()
                }
            },
            enabled = enabled
        ) {
            Text(text = "Reset")
        }
    }

}