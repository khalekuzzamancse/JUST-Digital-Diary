package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthPasswordField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTextField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.ElevatedField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.RegistrationFormLabels


data class LoginFormData(
    val username: String="",
    val password: String="",
)
@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    state: LoginViewModel,
) {
    val data=state.data.collectAsState().value
    LoginForm(
        data=data,
        fieldModifier = Modifier.fillMaxWidth(),
        formModifier = modifier,
        onUserNameChanged = state::onUserNameChanged,
        onPasswordChanged = state::onPasswordChanged,
    )
}

@Composable
fun LoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    data: LoginFormData,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    LoginForm(
        fieldModifier = fieldModifier,
        formModifier = formModifier,
        userName = data.username,
        password = data.password,
        onUserNameChanged=onUserNameChanged,
        onPasswordChanged=onPasswordChanged
    )

}

@Composable
fun LoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    userName: String,
    onUserNameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
) {
    Column(
        modifier = formModifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
            AuthTextField(
                modifier = fieldModifier,
                label = RegistrationFormLabels.USER_NAME,
                value = userName,
                onValueChanged = onUserNameChanged,
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Default.Person4,
            )

            AuthPasswordField(
                modifier = fieldModifier,
                label = RegistrationFormLabels.PASSWORD,
                value = password,
                onValueChanged = onPasswordChanged,
            )



    }


}
