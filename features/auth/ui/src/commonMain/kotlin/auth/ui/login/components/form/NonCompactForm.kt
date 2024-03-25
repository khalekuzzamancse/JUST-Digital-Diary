package auth.ui.login.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import auth.ui.login.components.form.state.FormData
import auth.ui.login.components.form.state.LoginFormEvent
import common.ui.form_layout.FormLayout


@Composable
 internal fun NonCompactScreenLoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    data: FormData,
    event: LoginFormEvent,
) {
    NonCompactScreenLoginForm(
        fieldModifier = fieldModifier,
        formModifier = formModifier,
        userName = data.username,
        password = data.password,
        onUserNameChanged=event.onUserNameChanged,
        onPasswordChanged=event.onPasswordChanged
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
        Text("User Name")
        AuthTextField(
            modifier = fieldModifier,
            value = userName,
            onValueChanged = onUserNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Default.Person4,
        )
        Text("Password")
        AuthPasswordField(
            modifier = fieldModifier,
            value = password,
            onValueChanged = onPasswordChanged,
        )

    }


}
