package com.just.cse.digital_diary.two_zero_two_three.auth.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterSection(
    onRegisterSuccess: () -> Unit,
    viewModel: RegistrationViewModel,
) {
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.padding(16.dp).background(Color.Red),
        shadowElevation = 8.dp

    ){
        Column(
            modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
        ) {
            RegistrationForm(viewModel = viewModel)
            Button(
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                onClick = {
                    scope.launch {
                        val success=viewModel.onRegistrationRequest()
                        if (success){
                            delay(1000) //to show the success message
                            onRegisterSuccess()
                        }

                    }

                }
            ) {
                Text(text = "Register".uppercase())
            }
        }

    }

}
