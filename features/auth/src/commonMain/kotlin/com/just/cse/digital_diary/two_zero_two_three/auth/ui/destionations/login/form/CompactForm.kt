package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthPasswordField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTextField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationFormLabels




@Composable
internal fun CompactScreenLoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    data: LoginFormData,
    event:LoginFormEvent,
) {
    CompactScreenLoginForm(
        fieldModifier = fieldModifier,
        formModifier = formModifier,
        userName = data.username,
        password = data.password,
        onUserNameChanged=event.onUserNameChanged,
        onPasswordChanged=event.onPasswordChanged
    )

}

@Composable
private fun CompactScreenLoginForm(
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
