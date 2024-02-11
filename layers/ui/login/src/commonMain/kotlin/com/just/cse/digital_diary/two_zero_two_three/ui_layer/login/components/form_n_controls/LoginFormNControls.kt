package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form_n_controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.controls.LoginControls
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.LoginForm
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.state.FormData
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.state.LoginFormEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.header.LoginHeaderSection
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event.LoginEvent

/**
 * A [Stateless Component]
 * For the Login destination.
 * @param modifier a [Modifier] (optional)
 * @param data for the  [LoginFormData]
 * @param event for the  [LoginFormEvent]
 */
@Composable
 fun LoginFormNControls(
    modifier: Modifier = Modifier,
    data: FormData,
    formEvent: LoginFormEvent,
    onControlEvent: (LoginEvent.LoginControlsEvent) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier,
            shadowElevation = 6.dp,
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Box(Modifier.align(Alignment.CenterHorizontally)) {
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
