package auth.common

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import common.newui.CustomTextField

//Preview is in the below
//TODO: Auth Filed Section -- Auth Filed Section -- Auth Filed Section
//TODO: Auth Filed Section -- Auth Filed Section -- Auth Filed Section
@Composable
internal fun AuthPasswordField(
    modifier: Modifier,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    enableVisibility: Boolean = true,
) {
    var show by remember { mutableStateOf(false) }
    CustomTextField(
        modifier = modifier,
        label = label,
        value = value,
        visualTransformation = if (show) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = Icons.Outlined.Lock,
        keyboardType = KeyboardType.Password,
        onValueChanged = onValueChanged,
        trailingIcon = {
            if (enableVisibility) {
                Icon(
                    imageVector = if (show) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "trailing icon",
                    modifier = it.clickable {
                        show = !show
                    }
                )
            }

        }
    )

}
