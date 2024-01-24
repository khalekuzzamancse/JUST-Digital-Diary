package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.destination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.controls.LoginControls
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.controls.LoginControlsLoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.LoginForm
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.LoginFormEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.header.LoginHeaderSection
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.event.DestinationLoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.event.LoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.states.FormData
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.viewmodel.LoginDestinationViewModel

@Composable
fun LoginDestination(
    viewModel: LoginDestinationViewModel,
    modifier: Modifier = Modifier,
) {
    val onEvent=viewModel::onEvent
    val data=viewModel.formData.collectAsState().value
    val formEvent=viewModel.formEvent
    Box(modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                SimpleTopBar(
                    title = "Login",
                    onNavigationIconClick = { onEvent(DestinationLoginModuleEvent.ExitRequest) },
                    navigationIcon = null
                )
            }
        ) {
            LoginFormNControls(
                modifier = Modifier.padding(it).fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                data = data,
                onControlEvent =onEvent,
                formEvent = formEvent
            )
        }
    }

}
@Composable
private fun LoginDestination(
    modifier: Modifier = Modifier,
    data: FormData,
    formEvent: LoginFormEvent,
    onEvent: (LoginModuleEvent) -> Unit,
) {
    Box(modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                SimpleTopBar(
                    title = "Login",
                    onNavigationIconClick = { onEvent(DestinationLoginModuleEvent.ExitRequest) },
                    navigationIcon = null
                )
            }
        ) {
            LoginFormNControls(
                modifier = Modifier.padding(it).fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                data = data,
                onControlEvent =onEvent,
                formEvent = formEvent
            )
        }
    }

}

/**
 * A [Stateless Component]
 * For the Login destination.
 * @param modifier a [Modifier] (optional)
 * @param data for the  [LoginFormData]
 * @param event for the  [LoginFormEvent]
 */
@Composable
private fun LoginFormNControls(
    modifier: Modifier = Modifier,
    data: FormData,
    formEvent: LoginFormEvent,
    onControlEvent: (LoginControlsLoginModuleEvent) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHeaderSection()
        Surface(
            modifier = Modifier.padding(8.dp),
            shadowElevation = 6.dp,
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Box(Modifier.widthIn(max = 500.dp).align(Alignment.CenterHorizontally)) {
                    LoginForm(
                        data = data,
                        fieldModifier = Modifier.fillMaxWidth(),
                        event = formEvent
                    )
                }
                Spacer(Modifier.height(16.dp))
                LoginControls(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onEvent = onControlEvent

                )

            }
        }
    }
}
