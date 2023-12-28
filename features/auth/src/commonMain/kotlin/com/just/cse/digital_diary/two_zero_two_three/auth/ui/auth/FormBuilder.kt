package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.form.FormLayout
import com.just.cse.digital_diary.features.common_ui.form.LabelLessTextField
import com.just.cse.digital_diary.features.common_ui.form.LabelLessTextFieldProperties
import com.just.cse.digital_diary.features.common_ui.form.LabelLessTextFieldState
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.LoginFieldsState



@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    verticalGap: Dp,
) {
    val state = remember {
        LoginFieldsState()
    }
    val isHorizontal = calculateWindowSizeClass().widthSizeClass != WindowWidthSizeClass.Compact

    LoginForm(
        fieldModifier = Modifier,
        formModifier = modifier,
        isHorizontal = isHorizontal,
        userNameState = state.userName.collectAsState().value,
        onUserNameChanged = state::onUserNameChanged,
        passwordState = state.password.collectAsState().value,
        onPasswordChanged = state::onPasswordChanged,
        showPassword = state.showPassword.collectAsState().value,
        onTogglePasswordVisibility = state::onTogglePasswordVisibility,
        verticalGap=verticalGap
    )
}

@Composable
fun LoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    isHorizontal: Boolean,
    userNameState: LabelLessTextFieldState,
    onUserNameChanged: (String) -> Unit,
    passwordState: LabelLessTextFieldState,
    onPasswordChanged: (String) -> Unit,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    verticalGap: Dp,
) {
    if (isHorizontal) {
        FormLayout(
            eachRow1stChildMaxWidth = 200.dp,
            modifier = formModifier,
            verticalGap = verticalGap,
            horizontalGap = 8.dp
        ) {
            Text(text = "User Name")
            AuthInputField(
                modifier = fieldModifier,
                leadingIcon = Icons.Default.Person,
                state = userNameState,
                onValueChanged = onUserNameChanged
            )
            Text(text = "Password")
            AuthInputField(
                modifier = fieldModifier,
                leadingIcon = Icons.Default.Lock,
                state = passwordState,
                onValueChanged = onPasswordChanged,
                trailingIcon = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                onTrailingIconClicked = onTogglePasswordVisibility,
                visualTransformation = if (showPassword) null else PasswordVisualTransformation()
            )
        }

    } else {
        Column(fieldModifier) {
            Text(text = "User Name")
            AuthInputField(
                modifier = fieldModifier,
                leadingIcon = Icons.Default.Person,
                state = userNameState,
                onValueChanged = onUserNameChanged
            )
            Text(text = "Password")
            AuthInputField(
                modifier = fieldModifier,
                leadingIcon = Icons.Default.Lock,
                state = passwordState,
                onValueChanged = onPasswordChanged,
                trailingIcon = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                onTrailingIconClicked = onTogglePasswordVisibility
            )
        }
    }

}

@Composable
fun AuthInputField(
    modifier: Modifier = Modifier,
    trailingIcon: ImageVector? = null,
    visualTransformation: VisualTransformation? = null,
    onTrailingIconClicked: (() -> Unit)? = null,
    state: LabelLessTextFieldState,
    leadingIcon: ImageVector,
    onValueChanged: (String) -> Unit,
) {
    LabelLessTextField(
        modifier = modifier,
        properties = LabelLessTextFieldProperties(
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified
            )
        ),
        state = state,
        onValueChanged = onValueChanged,
        onTrailingIconClick = onTrailingIconClicked
    )
}
