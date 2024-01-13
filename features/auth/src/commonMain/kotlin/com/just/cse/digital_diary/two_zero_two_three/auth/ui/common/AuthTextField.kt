package com.just.cse.digital_diary.two_zero_two_three.auth.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldProperties
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldState
import com.just.cse.digital_diary.features.common_ui.form.FormTextInput
import com.just.cse.digital_diary.features.common_ui.form.formInputFieldIcon

@Composable
fun AuthPasswordField(
    modifier: Modifier,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    errorMessage: String? = null,
    shape: Shape = TextFieldDefaults.shape,
    color: TextFieldColors = TextFieldDefaults.colors(),
) {
    var showPassword by remember { mutableStateOf(true) }
    val trailingIcon = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
    val leadingIcon = remember { Icons.Default.Lock }
    val keyboardType = remember { KeyboardType.Password }
    val visualTransformation = if(showPassword)VisualTransformation.None else PasswordVisualTransformation()
    val onTogglePassword: () -> Unit = remember {
        { showPassword = !showPassword }
    }

    val content: @Composable ColumnScope.() -> Unit =
        if (errorMessage == null) {
            @Composable {
                Text(text = label)
                Spacer(Modifier.height(8.dp))
                TextField(
                    shape = shape,
                    modifier = modifier,
                    singleLine = true,
                    value = value,
                    onValueChange = onValueChanged,
                    leadingIcon = formInputFieldIcon(icon = leadingIcon),
                    trailingIcon = formInputFieldIcon(trailingIcon, onTogglePassword),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                    visualTransformation = visualTransformation,
                    colors = color,
                )
            }
        } else {
            @Composable {
                Text(text = label)
                Spacer(Modifier.height(8.dp))
                TextField(
                    shape = shape,
                    modifier = modifier,
                    singleLine = true,
                    value = value,
                    onValueChange = onValueChanged,
                    leadingIcon = formInputFieldIcon(icon = leadingIcon),
                    trailingIcon = formInputFieldIcon(trailingIcon, onTogglePassword),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                    visualTransformation = visualTransformation,
                    colors = color,
                    isError = true,
                    supportingText = {
                        Text(
                            text = errorMessage
                        )
                    },
                )
            }


    }
    Column(modifier = modifier) {
        content()
    }
}

@Composable
fun AuthTextField(
    modifier: Modifier,
    label: String,
    value: String,
    leadingIcon: ImageVector?,
    keyboardType: KeyboardType,
    color: TextFieldColors = TextFieldDefaults.colors(),
    shape: Shape = TextFieldDefaults.shape,
    errorMessage: String? = null,
    onValueChanged: (String) -> Unit,
) {
    val content: @Composable ColumnScope.() -> Unit = if (errorMessage == null) @Composable {
        {
            Text(text = label)
            Spacer(Modifier.height(8.dp))
            TextField(
                shape = shape,
                modifier = modifier,
                value = value,
                onValueChange = onValueChanged,
                leadingIcon = formInputFieldIcon(icon = leadingIcon),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                colors = color,
            )

        }
    } else @Composable {
        {
            Text(text = label)
            Spacer(Modifier.height(8.dp))
            TextField(
                shape = shape,
                modifier = modifier,
                value = value,
                onValueChange = onValueChanged,
                leadingIcon = formInputFieldIcon(icon = leadingIcon),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                colors = color,
                isError = true,
                supportingText = {
                    Text(
                        text = errorMessage
                    )
                },
            )
        }

    }
    Column(modifier = modifier) {
        content()
    }


}

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    properties: FormTextFieldProperties,
    state: FormTextFieldState,
    isHorizontalOrientation: Boolean,
    labelMinWidth: Dp,
    onValueChanged: (String) -> Unit,
    onTrailingIconClick: () -> Unit = {},
) {

    FormTextInput(
        modifier = Modifier,
        properties = properties.copy(
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified
            ),
        ),
        value = state.value,
        errorMessage = state.errorMessage,
        onValueChanged = onValueChanged,
        onTrailingIconClick = onTrailingIconClick,
        labelFieldLayout = { label, field ->
            if (isHorizontalOrientation) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    label(Modifier.defaultMinSize(minWidth = labelMinWidth))
                    Spacer(Modifier.width(4.dp))
                    field(Modifier.fillMaxWidth().background(Color.Blue))
                }
            } else {
                Column {
                    label(Modifier)
                    Spacer(Modifier.height(2.dp))
                    field(Modifier.fillMaxWidth().background(Color.Blue))
                }
            }

        }
    )
}
