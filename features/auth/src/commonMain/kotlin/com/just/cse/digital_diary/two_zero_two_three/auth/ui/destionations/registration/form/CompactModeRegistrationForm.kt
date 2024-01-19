package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person4
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthDropDownMenu
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthPasswordField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTextField


@Composable
 internal fun CompactModeRegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: RegistrationFormData,
    event: RegisterFormEvent,
) {

    RegistrationForm(
        modifier = modifier,
        fieldModifier = fieldModifier,
        name = data.name,
        onNameChanged = event.onNameChanged,
        email = data.email,
        onEmailChanged = event.onEmailChanged,
        username = data.username,
        onUserNameChanged = event.onUserNameChanged,
        password = data.password,
        onPasswordChanged = event.onPasswordChanged,
        departments = listOf("CSE", "EEE"),
        dept = data.dept,
        onDeptChanged = event.onDeptChanged,
        confirmedPassword = data.confirmPassword,
        onConfirmedPassword = event.onConfirmedPassword
    )

}

@Composable
private fun RegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    name: String,
    onNameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    username: String,
    onUserNameChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    departments: List<String>,
    dept: String,
    onDeptChanged: (String) -> Unit,
    confirmedPassword: String,
    onConfirmedPassword: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

            AuthTextField(
                modifier = fieldModifier,
                label = RegistrationFormLabels.FULL_NAME,
                value = name,
                onValueChanged = onNameChanged,
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Default.Person,
            )


           AuthTextField(
               modifier = fieldModifier,
               label = RegistrationFormLabels.EMAIL,
               value = email,
               onValueChanged = onEmailChanged,
               keyboardType = KeyboardType.Email,
               leadingIcon = Icons.Default.Email,
           )


           AuthTextField(
               modifier = fieldModifier,
               label = RegistrationFormLabels.USER_NAME,
               value = username,
               onValueChanged = onUserNameChanged,
               keyboardType = KeyboardType.Text,
               leadingIcon = Icons.Default.Person4,
           )

           AuthDropDownMenu(
               modifier = fieldModifier,
               label = RegistrationFormLabels.Dept,
               options = departments,
               onOptionSelected = onDeptChanged,
               selected = dept
           )

           AuthPasswordField(
               modifier = fieldModifier,
               label = RegistrationFormLabels.PASSWORD,
               value = password,
               onValueChanged = onPasswordChanged,
           )

           AuthPasswordField(
               modifier = fieldModifier,
               label = RegistrationFormLabels.CONFIRMED_PASSWORD,
               value = confirmedPassword,
               onValueChanged = onConfirmedPassword,
           )


    }

}





