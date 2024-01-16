package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationControls
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationForm
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.header.RegistrationHeaderSection

@Composable
fun RegisterSection(
    modifier: Modifier,
    allowFooterBackNavigation: Boolean = false,
    onExitRequest: () -> Unit,
    viewModel: RegistrationViewModel,
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
                fieldModifier = Modifier,
                data = viewModel.data.collectAsState().value,
                onNameChanged = viewModel::onFullNameChanged,
                onEmailChanged = viewModel::onEmailChanged,
                onUserNameChanged = viewModel::onUsernameChanged,
                onPasswordChanged = viewModel::onPasswordChanged,
                onDeptChanged = viewModel::onDeptChanged,
                onConfirmedPassword = viewModel::onConfirmedPasswordChanged
            )
        }
        Spacer(Modifier.height(24.dp))
        Column(Modifier.padding(start = 16.dp).align(Alignment.CenterHorizontally),){
            AnimatedVisibility(
                visible = allowFooterBackNavigation
            ) {
                LoginSection(
                    modifier =Modifier,
                    onLoginRequest = onExitRequest
                )
            }
        }

        RegistrationControls(
            modifier = Modifier.widthIn(min = 200.dp, max = 300.dp)
                .align(Alignment.CenterHorizontally),
            onRegistrationRequest = viewModel::onRegistrationRequest
        )

    }
}

@Composable
private fun LoginSection(
    modifier: Modifier,
    onLoginRequest: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max), // add this modifier
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already Have an account ?")
            Spacer(Modifier.width(4.dp))
            TextButton(onClick = onLoginRequest) {
                Text(
                    text = "Login"
                )
            }
        }

    }


}