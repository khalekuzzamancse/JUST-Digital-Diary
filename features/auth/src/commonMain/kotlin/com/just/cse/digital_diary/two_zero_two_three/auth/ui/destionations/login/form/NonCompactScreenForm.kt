package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthPasswordField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTextField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationFormLabels
import com.just.cse.digital_diary.two_zero_two_three.common_ui.form_layout.FormLayout


@Composable
 internal fun NonCompactScreenLoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    data: LoginFormData,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    NonCompactScreenLoginForm(
        fieldModifier = fieldModifier,
        formModifier = formModifier,
        userName = data.username,
        password = data.password,
        onUserNameChanged=onUserNameChanged,
        onPasswordChanged=onPasswordChanged
    )

}

@Composable
private fun NonCompactScreenLoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    userName: String,
    onUserNameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
) {
    FormLayout(
        eachRow1stChildMaxWidth=200.dp,
        verticalGap=8.dp,
        horizontalGap=4.dp,
        modifier = formModifier.fillMaxWidth(),
    ){
        Text(RegistrationFormLabels.USER_NAME)
        AuthTextField(
            modifier = fieldModifier,
            value = userName,
            onValueChanged = onUserNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Default.Person4,
        )
        Text( RegistrationFormLabels.PASSWORD)
        AuthPasswordField(
            modifier = fieldModifier,
            value = password,
            onValueChanged = onPasswordChanged,
        )

    }


}
