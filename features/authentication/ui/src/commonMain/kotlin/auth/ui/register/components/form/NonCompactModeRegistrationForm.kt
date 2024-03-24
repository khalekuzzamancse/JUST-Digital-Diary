package auth.ui.register.components.form

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.ui.register.components.form.event.RegisterFormEvent
import auth.ui.register.components.form.state.RegistrationFormData
import auth.ui.register.components.input.AuthPasswordField
import auth.ui.register.components.input.AuthTextField
import com.just.cse.digital_diary.two_zero_two_three.common_ui.form_layout.FormLayout


@Composable
internal fun NonCompactModeRegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: RegistrationFormData,
    event: RegisterFormEvent
) {

    NonCompactModeRegistrationForm(
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

/*
As per the custom layout measurements it will takes the full width,
so when use it define a max width for it
 */
@Composable
private fun NonCompactModeRegistrationForm(
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
        FormLayout(
            eachRow1stChildMaxWidth = 200.dp,
            verticalGap = 8.dp,
            horizontalGap = 4.dp,
            modifier = modifier,
        ) {
            Text(text = RegistrationFormLabels.FULL_NAME)
            AuthTextField(
                modifier = fieldModifier,
                value = name,
                onValueChanged = onNameChanged,
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Default.Person,
            )
            Text(text = RegistrationFormLabels.EMAIL)
            AuthTextField(
                modifier = fieldModifier,
                value = email,
                onValueChanged = onEmailChanged,
                keyboardType = KeyboardType.Email,
                leadingIcon = Icons.Default.Email,
            )
            Text(text = RegistrationFormLabels.USER_NAME)
            AuthTextField(
                modifier = fieldModifier,
                value = username,
                onValueChanged = onUserNameChanged,
                keyboardType = KeyboardType.Text,
                leadingIcon = Icons.Default.Person4,
            )
//            Text(text = RegistrationFormLabels.Dept)
//            AuthDropDownMenu(
//                modifier = fieldModifier,
//                options = departments,
//                onOptionSelected = onDeptChanged,
//                selected = dept
//            )
            Text(text = RegistrationFormLabels.PASSWORD)
            AuthPasswordField(
                modifier = fieldModifier,
                value = password,
                onValueChanged = onPasswordChanged,
            )
            Text(text = RegistrationFormLabels.CONFIRMED_PASSWORD)
            AuthPasswordField(
                modifier = fieldModifier,
                value = confirmedPassword,
                onValueChanged = onConfirmedPassword,
            )
        }

}





