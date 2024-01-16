package com.just.cse.digital_diary.two_zero_two_three.auth.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp




@Composable
fun NonCompactAuthPasswordField(
    modifier: Modifier,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    errorMessage: String? = null,
    shape: Shape = TextFieldDefaults.shape,
) {
    var showPassword by remember { mutableStateOf(true) }
    val trailingIcon = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff
    val leadingIcon = remember { Icons.Default.Lock }
    val keyboardType = remember { KeyboardType.Password }
    val visualTransformation =
        if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    val onTogglePassword: () -> Unit = remember {
        { showPassword = !showPassword }
    }
    val colors=TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
    )
    val content: @Composable ColumnScope.() -> Unit =
        if (errorMessage == null) {
            @Composable {
                Text(text = label)
                TextField(
                    label=null,
                    shape = shape,
                    modifier = modifier,
                    singleLine = true,
                    value = value,
                    onValueChange = onValueChanged,
                    leadingIcon = {
                        Icon(imageVector = leadingIcon, contentDescription = null)
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onTogglePassword()
                            })
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                    visualTransformation = visualTransformation,
                    colors = colors,
                )
            }
        } else {
            @Composable {
                Text(text = label)
                TextField(
                    label=null,
                    shape = shape,
                    modifier = modifier,
                    singleLine = true,
                    value = value,
                    onValueChange = onValueChanged,
                    leadingIcon = {
                        Icon(imageVector = leadingIcon, contentDescription = null)
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onTogglePassword()
                            })
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                    visualTransformation = visualTransformation,
                    colors = colors,
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
fun NonCompactAuthTextField(
    modifier: Modifier,
    label: String,
    value: String,
    leadingIcon: ImageVector?,
    keyboardType: KeyboardType,
    shape: Shape = TextFieldDefaults.shape,
    errorMessage: String? = null,
    onValueChanged: (String) -> Unit,
) {
    val colors=TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
    )
    val content: @Composable ColumnScope.() -> Unit = if (errorMessage == null) @Composable {
        {
            Text(text = label)
            TextField(
                label=null,
                shape = shape,
                modifier = modifier,
                value = value,
                onValueChange = onValueChanged,
                leadingIcon = {
                    if (leadingIcon != null) {
                        Icon(imageVector = leadingIcon, contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                colors = colors,
            )

        }
    } else @Composable {
        {
            Text(text = label)
            TextField(
                label=null,
                shape = shape,
                modifier = modifier,
                value = value,
                onValueChange = onValueChanged,
                leadingIcon = {
                    if (leadingIcon != null) {
                        Icon(imageVector = leadingIcon, contentDescription = null)
                    }
                },

                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                colors =colors,
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