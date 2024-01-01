package com.just.cse.digital_diary.two_zero_two_three.auth.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldState
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.LoginForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object LoginFormLabels {
    const val USER_NAME = "User Name or Email"
    const val PASSWORD = "Password"
    fun getRowWidth(measurer: TextMeasurer): Int {
        val userNameLabelWidth = measurer.measure(USER_NAME).size.width
        val passwordLabelWidth = measurer.measure(PASSWORD).size.width
        println("userNameLabelWidth:$userNameLabelWidth, passwordLabelWidth:$passwordLabelWidth")
        return maxOf(userNameLabelWidth, passwordLabelWidth)
    }
}

class LoginViewModel() {
    private val _userName = MutableStateFlow(FormTextFieldState(value = ""))
    val userName = _userName.asStateFlow()
    private val _password = MutableStateFlow(FormTextFieldState())
    val password = _password.asStateFlow()
    fun onUserNameChanged(userName: String) {
        _userName.update { it.copy(value = userName) }
    }

    fun onPasswordChanged(password: String) {
        _password.update { it.copy(value = password) }
    }

    fun onLoginRequest() {
        //  println("onLoginRequest, ${userName.value}, ${password.value}")
    }

}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LoginSection(
    modifier: Modifier = Modifier,
    onRegisterRequest: () -> Unit={},
    onLoginRequest: () -> Unit={},
    onPasswordResetRequest: () -> Unit={},
) {

    val isHorizontal = calculateWindowSizeClass().widthSizeClass != WindowWidthSizeClass.Compact
    val w = calculateWindowSizeClass().widthSizeClass

    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =Alignment.CenterHorizontally
    ) {
        if (w == WindowWidthSizeClass.Expanded) {
//            WelcomeExpandedScreen(
//                modifier.padding(32.dp).weight(1f).align(Alignment.CenterVertically)
//            )
        }
        LoginSectionHeader(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        LoginFieldsNControls(
            modifier = Modifier,
            isHorizontal = isHorizontal,
            onRegisterRequest = onRegisterRequest,
            onLoginRequest =onLoginRequest,
            onPasswordResetRequest = onPasswordResetRequest
        )

    }

}

@Composable
fun LoginFieldsNControls(
    modifier: Modifier,
    isHorizontal: Boolean,
    onRegisterRequest: () -> Unit,
    onPasswordResetRequest: () -> Unit,
    onLoginRequest: () -> Unit,
) {

    Surface(
        modifier=modifier.padding(16.dp).background(Color.Red),
        shadowElevation = 8.dp

    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LoginForm(
                modifier = Modifier.widthIn(max = 500.dp),
                verticalGap = 8.dp
            )
            Spacer(Modifier.height(16.dp))
            ForgetPassword(
                modifier = Modifier.align(Alignment.End),
                onPasswordResetRequest = onPasswordResetRequest
            )
            VerticalSpacer()
            LoginOrSignUp(
                modifier = if (isHorizontal)
                    Modifier.padding(start = 16.dp)
                else Modifier.padding(start = 16.dp),
                onRegisterRequest = onRegisterRequest,
                onLoginRequest = onLoginRequest
            )

        }
    }


}


//
@Composable
private fun LoginOrSignUp(
    modifier: Modifier,
    onRegisterRequest: () -> Unit,
    onLoginRequest: () -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max), // add this modifier
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.width(16.dp))
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't Have an account ?")
            Spacer(Modifier.width(4.dp))
            TextButton(onClick = onRegisterRequest) {
                Text(
                    text = "Register"
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginRequest
        ) {
            Text(text = "Login".uppercase())
        }

    }


}

@Composable
private fun ForgetPassword(
    modifier: Modifier,
    onPasswordResetRequest: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = onPasswordResetRequest) {
            Text(
                text = "Forget Password ?",
            )
        }
    }
}


@Composable
fun VerticalSpacer() {
    Spacer(
        modifier = Modifier
            .height(8.dp)

    )
}
//