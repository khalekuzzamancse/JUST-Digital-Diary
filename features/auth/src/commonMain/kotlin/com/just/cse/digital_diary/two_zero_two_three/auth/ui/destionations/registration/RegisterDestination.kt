package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.controls.LoginSection
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.controls.RegistrationControls
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegisterFormEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationForm
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationFormData
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.header.RegistrationHeaderSection
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
fun RegisterDestination(
    modifier: Modifier = Modifier,
    onExitRequest: () -> Unit,
    data: RegistrationFormData,
    event: RegisterFormEvent,
    onRegisterRequest: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Registration Form",
                onNavigationIconClick = onExitRequest
            )
        }
    ) {
        RegisterFormNControls(
            modifier = modifier.padding(it),
            onExitRequest = onExitRequest,
            data = data,
            onRegisterRequest = onRegisterRequest,
            event = event,

            )

    }

}

@Composable
private fun RegisterFormNControls(
    modifier: Modifier,
    data: RegistrationFormData,
    event: RegisterFormEvent,
    onRegisterRequest: () -> Unit,
    onExitRequest: () -> Unit,
) {

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        RegistrationHeaderSection(
            modifier = Modifier.widthIn(max = 700.dp).align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(24.dp))
        //wrapping the form with box to make it centered
        //liming the max width of the form to avoid bug because there is a bug on measurement form
        Box(Modifier.widthIn(max = 500.dp).align(Alignment.CenterHorizontally)) {
            RegistrationForm(
                modifier = Modifier.padding(8.dp),
                fieldModifier = Modifier.fillMaxWidth(),
                data = data,
                event = event
            )
        }
        Spacer(Modifier.height(24.dp))
        Column(Modifier.padding(start = 16.dp).align(Alignment.CenterHorizontally)) {
            LoginSection(
                modifier = Modifier,
                onLoginRequest = onExitRequest
            )
        }
        RegistrationControls(
            modifier = Modifier.widthIn(min = 200.dp, max = 300.dp)
                .align(Alignment.CenterHorizontally),
            onRegistrationRequest = onRegisterRequest
        )

    }
}

