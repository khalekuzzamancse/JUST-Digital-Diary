package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationControls(
    modifier: Modifier,
    onRegistrationRequest: () -> Unit,
) {
    Button(modifier = modifier,
        elevation = ButtonDefaults
            .buttonElevation(defaultElevation = 8.dp, focusedElevation = 8.dp),
        onClick =onRegistrationRequest) {
        Text(text = "Register".uppercase())
    }
}
@Composable
 fun LoginSection(
    modifier: Modifier,
    onLoginRequest: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max), // add this modifier
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already Have an account ?")
            Spacer(Modifier.width(4.dp))
            TextButton(onClick = onLoginRequest) {
                Text(
                    text = "Login"
                )
            }
        }

    }


}