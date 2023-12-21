package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextFieldProperties
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextFieldState


@Composable
fun LoginFields(
    modifier: Modifier = Modifier,
    userName: FormTextFieldState,
    password: FormTextFieldState,
    labelRowWidth: Dp,
    isHorizontal: Boolean,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    var passwordFieldProperty by remember {
        mutableStateOf(
            FormTextFieldProperties(
                label = LoginFormLabels.PASSWORD,
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
        AuthTextField(
            properties = FormTextFieldProperties(
                label = LoginFormLabels.USER_NAME,
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            ),
            state = userName,
            onValueChanged = onUserNameChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth

        )
        Spacer(Modifier.height(8.dp))
        AuthTextField(
            properties = passwordFieldProperty,
            state = password,
            onValueChanged = onPasswordChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth,
            onTrailingIconClick = togglePasswordVisibility
        )
    }


}