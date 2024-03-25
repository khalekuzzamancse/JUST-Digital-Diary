package auth.ui.register.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import auth.ui.register.components.events.RegisterControlEvents
import auth.ui.register.components.form.event.RegisterFormEvent
import auth.ui.register.components.form.state.RegistrationFormData
import auth.ui.register.components.form_and_controls.RegisterFormNControls


@Composable
fun RegisterDestination(
    modifier: Modifier = Modifier,
    viewModel: RegisterDestinationViewModel,
    onEvent: (RegisterControlEvents) -> Unit
) {
    RegisterDestination(
        modifier = modifier,
        onEvent = onEvent,
        data = viewModel.formData.collectAsState().value,
        formEvent = viewModel.formEvent
    )
}

@Composable
private fun RegisterDestination(
    modifier: Modifier = Modifier,
    onEvent: (RegisterControlEvents) -> Unit,
    data: RegistrationFormData,
    formEvent: RegisterFormEvent,
) {
    RegisterFormNControls(
        modifier = modifier,
        onLoginRequest = {},
        data = data,
        onRegisterRequest = {
            onEvent(RegisterControlEvents.RegisterRequest)
        },
        event = formEvent
    )

}

