package com.just.cse.digital_diary.two_zero_two_three.auth.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person4
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthDropDownMenu
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthPasswordField
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTextField

@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
) {
    val data = viewModel.data.collectAsState().value
    RegistrationForm(
        modifier = modifier,
        fieldModifier = fieldModifier.fillMaxWidth(),
        data = data,
        onNameChanged = viewModel::onFullNameChanged,
        onEmailChanged = viewModel::onEmailChanged,
        onUserNameChanged = viewModel::onUsernameChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onDeptChanged = viewModel::onDeptChanged,
        onConfirmedPassword = viewModel::onConfirmedPasswordChanged
    )

}
data class RegistrationFormData(
    val name: String="",
    val email: String = "",
    val username: String = "",
    val dept:String="",
    val password: String = "",
    val confirmPassword: String = ""
)

object RegistrationFormLabels {
    const val FULL_NAME = "Full Name"
    const val EMAIL = "Email"
    const val USER_NAME = "User Name"
    const val Dept = "Department"
    const val PASSWORD = "Password"
    const val CONFIRMED_PASSWORD = "Confirm Password"
}

@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: RegistrationFormData,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onDeptChanged: (String) -> Unit,
    onConfirmedPassword: (String) -> Unit,
) {

    RegistrationForm(
        modifier = modifier,
        fieldModifier = fieldModifier,
        name = data.name,
        onNameChanged = onNameChanged,
        email = data.email,
        onEmailChanged = onEmailChanged,
        username = data.username,
        onUserNameChanged = onUserNameChanged,
        password = data.password,
        onPasswordChanged = onPasswordChanged,
        departments = listOf("CSE", "EEE"),
        dept = data.dept,
        onDeptChanged = onDeptChanged,
        confirmedPassword = data.confirmPassword,
        onConfirmedPassword = onConfirmedPassword
    )

}

@Composable
fun RegistrationForm(
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
        modifier = modifier.fillMaxWidth(),
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





