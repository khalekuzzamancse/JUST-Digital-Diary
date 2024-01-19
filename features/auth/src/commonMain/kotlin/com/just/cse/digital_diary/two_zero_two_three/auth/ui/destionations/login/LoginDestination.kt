package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTopBar
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.controls.LoginControls
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginForm
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginFormData
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginFormEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.header.LoginHeaderSection


@Composable
fun LoginDestination(
    modifier: Modifier = Modifier,
    onLoginRequest:()->Unit,
    data: LoginFormData,
    event: LoginFormEvent,
    onRegistrationFormRequest: () -> Unit,
) {
    Box(modifier.fillMaxSize()){
        Scaffold(
            topBar = {
                AuthTopBar(
                    title = "Login",
                    onNavigationIconClick = {},
                    navigationIcon = null
                )
            }
        ) {
            LoginFormNControls(
                modifier = Modifier.padding(it).fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                onRegistrationRequest = onRegistrationFormRequest,
                onLoginRequest = onLoginRequest,
                data = data,
                event = event
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
    onLoginRequest:()->Unit,
    data: LoginFormData,
    event: LoginFormEvent,
    onRegistrationRequest: () -> Unit
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
                        event = event
                    )
                }
                Spacer(Modifier.height(16.dp))
                LoginControls(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onRegisterRequest = onRegistrationRequest,
                    onLoginRequest = onLoginRequest,
                    onPasswordResetRequest = { },
                )

            }
        }
    }
}
