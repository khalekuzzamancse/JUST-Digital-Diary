package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterSection(
    onRegisterSuccess: () -> Unit,
    viewModel: RegistraionViewModel,
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ) {
        RegistrationHeaderSection()
        Spacer(Modifier.height(24.dp))
        Surface(
            modifier = Modifier.padding(16.dp),
            shadowElevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                RegistrationForm(viewModel = viewModel)
                Spacer(Modifier.height(48.dp))
                Button(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        focusedElevation = 8.dp,

                        ),
                    onClick = {
                        scope.launch {
                            val success = viewModel.onRegistrationRequest()
                            if (success) {
                                delay(1000) //to show the success message
                                onRegisterSuccess()
                            }

                        }

                    }
                ) {
                    Text(text = "Register".uppercase())
                }
                Spacer(Modifier.height(24.dp))
            }

        }


    }

}
