package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form_and_controls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.controls.LoginSection
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.controls.RegistrationControls
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.RegistrationForm
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.event.RegisterFormEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.state.RegistrationFormData
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.header.RegistrationHeaderSection

@Composable
 fun RegisterFormNControls(
    modifier: Modifier,
    data: RegistrationFormData,
    event: RegisterFormEvent,
    onRegisterRequest: () -> Unit,
    onLoginRequest: () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        //wrapping the form with box to make it centered
        //liming the max width of the form to avoid bug because there is a bug on measurement form
        Box(Modifier.widthIn(max = 1000.dp).align(Alignment.CenterHorizontally)) {
            RegistrationForm(
                modifier =Modifier.fillMaxWidth(),
                fieldModifier = Modifier.fillMaxWidth(),
                data = data,
                event = event
            )
        }
        Spacer(Modifier.height(24.dp))
//        Column(Modifier.padding(start = 16.dp).align(Alignment.CenterHorizontally)) {
//            LoginSection(
//                modifier = Modifier,
//                onLoginRequest = onLoginRequest
//            )
//        }
        RegistrationControls(
            modifier = Modifier.widthIn(min = 200.dp, max = 300.dp)
                .align(Alignment.CenterHorizontally),
            onRegistrationRequest = onRegisterRequest
        )

    }
}