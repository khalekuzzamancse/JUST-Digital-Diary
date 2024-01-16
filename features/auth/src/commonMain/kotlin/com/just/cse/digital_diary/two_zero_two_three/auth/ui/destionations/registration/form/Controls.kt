package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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