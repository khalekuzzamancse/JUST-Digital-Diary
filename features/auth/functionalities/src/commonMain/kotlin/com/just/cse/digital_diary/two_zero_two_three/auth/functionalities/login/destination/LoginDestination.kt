package com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.login.destination

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.login.viewmodel.LoginDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.state.FormData
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.state.LoginFormEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form_n_controls.LoginFormNControls
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event.LoginEvent

@Composable
fun LoginDestination(
    viewModel: LoginDestinationViewModel,
    modifier: Modifier = Modifier,
    onEvent: (LoginEvent) -> Unit,
) {
    val data = viewModel.formData.collectAsState().value
    val formEvent = viewModel.formEvent
    LoginDestination(
        modifier = modifier,
        data = data,
        formEvent = formEvent,
        onEvent = onEvent
    )

}

@Composable
private fun LoginDestination(
    modifier: Modifier = Modifier,
    data: FormData,
    formEvent: LoginFormEvent,
    onEvent: (LoginEvent) -> Unit,
) {
    LoginFormNControls(
        modifier = modifier,
        data = data,
        onControlEvent = onEvent,
        formEvent = formEvent
    )
}

