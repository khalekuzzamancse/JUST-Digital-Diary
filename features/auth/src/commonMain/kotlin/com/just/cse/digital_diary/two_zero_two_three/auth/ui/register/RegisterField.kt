package com.just.cse.digital_diary.two_zero_two_three.auth.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldProperties
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldState
import com.just.cse.digital_diary.features.common_ui.form.MyDropDownMenu
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.InputTextField

@Composable
fun RegistrationFields(
    modifier: Modifier = Modifier,
    name: FormTextFieldState,
    onNameChanged: (String) -> Unit,
    email: FormTextFieldState,
    onEmailChanged: (String) -> Unit,
    username: FormTextFieldState,
    onUserNameChanged: (String) -> Unit,
    password: FormTextFieldState,
    onPasswordChanged: (String) -> Unit,
    dept: FormTextFieldState,
    onDeptChanged: (String) -> Unit,
    confirmedPassword: FormTextFieldState,
    onConfirmedPassword: (String) -> Unit,
    labelRowWidth: Dp,
    isHorizontal: Boolean,
) {
    var passwordFieldProperty by remember {
        mutableStateOf(
            FormTextFieldProperties(
                label = RegistrationFormLabels.PASSWORD,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = Icons.Default.Lock,
                trailingIcon = Icons.Default.Visibility,
                keyboardType = KeyboardType.Password
            )
        )
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    val togglePasswordVisibility: () -> Unit = {
        showPassword = !showPassword
        passwordFieldProperty = if (showPassword) {
            passwordFieldProperty.copy(
                trailingIcon = Icons.Default.VisibilityOff,
                visualTransformation = VisualTransformation.None
            )
        } else {
            passwordFieldProperty.copy(
                trailingIcon = Icons.Default.Visibility,
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }

    Column(modifier = modifier) {
        InputTextField(
            properties = FormTextFieldProperties(
                label = RegistrationFormLabels.FULL_NAME,
                leadingIcon = Icons.Default.Person,
                keyboardType = KeyboardType.Text
            ),
            state = name,
            onValueChanged = onNameChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth
        )
        Spacer(Modifier.height(8.dp))
        InputTextField(
            properties = FormTextFieldProperties(
                label = RegistrationFormLabels.EMAIL,
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            ),
            state = email,
            onValueChanged = onEmailChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth
        )
        Spacer(Modifier.height(8.dp))

        InputTextField(
            properties = FormTextFieldProperties(
                label = RegistrationFormLabels.USER_NAME,
                leadingIcon = Icons.Default.Person,
                keyboardType = KeyboardType.Text
            ),
            state = username,
            onValueChanged = onUserNameChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth

        )
        Spacer(Modifier.height(8.dp))
        MyDropDownMenu(
            options = listOf("CSE","EEE"),
            onOptionSelected = onDeptChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth =labelRowWidth,
            selected = dept.value,
            leadingIcon = Icons.Default.School
        )
        Spacer(Modifier.height(8.dp))

        InputTextField(
            properties = passwordFieldProperty,
            state = password,
            onValueChanged = onPasswordChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth,
            onTrailingIconClick = togglePasswordVisibility
        )
        Spacer(Modifier.height(8.dp))
        InputTextField(
            properties = passwordFieldProperty.copy(
                label = RegistrationFormLabels.CONFIRMED_PASSWORD
            ),
            state = confirmedPassword,
            onValueChanged = onConfirmedPassword,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth,
            onTrailingIconClick = togglePasswordVisibility
        )
        Spacer(Modifier.height(8.dp))
    }


}