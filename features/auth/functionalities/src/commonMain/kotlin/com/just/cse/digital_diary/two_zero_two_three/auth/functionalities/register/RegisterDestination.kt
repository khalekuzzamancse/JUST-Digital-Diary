package com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.event.RegisterFormEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.state.RegistrationFormData
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form_and_controls.RegisterFormNControls
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.events.RegisterControlEvents
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.register.viewmodel.RegisterDestinationViewModel


@Composable
fun RegisterDestination(
    modifier: Modifier = Modifier,
    viewModel: RegisterDestinationViewModel,
    onEvent: (RegisterControlEvents) -> Unit
) {

//    LaunchedEffect(Unit) {
//        viewModel.shouldExit.collect {
//            if (it) {
//                onExitRequest()
//            }
//        }
//    }
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
        onLoginRequest = {

        },
        data = data,
        onRegisterRequest = {
            onEvent(RegisterControlEvents.RegisterRequest)
        },
        event = formEvent
    )

}

