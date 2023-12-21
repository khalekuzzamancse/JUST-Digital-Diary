package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextFieldState
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
fun LoginForm(
    modifier: Modifier = Modifier,
) {
    val viewModel = remember {
        LoginViewModel()
    }
    val density = LocalDensity.current.density
    val textMeasurer = rememberTextMeasurer()
    val labelRowWidth:Int = remember {
       val res=LoginFormLabels.getRowWidth(textMeasurer)
        (res*density).toInt()+80//fixing bug adding 80
     //   has a bug when use material theme ,fix it

    }


    val isHorizontal = calculateWindowSizeClass().widthSizeClass != WindowWidthSizeClass.Compact

    val w = calculateWindowSizeClass().widthSizeClass
    println("LabelWidth:$labelRowWidth")


    Row(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center,

    ) {
        if (w == WindowWidthSizeClass.Expanded) {
            WelcomeExpandedScreen(
                modifier.padding(32.dp).weight(1f).align(Alignment.CenterVertically)
            )
        }
        Column(
            modifier = modifier.padding(16.dp).width(IntrinsicSize.Min),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            JUSTLogoAndGreetings(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(16.dp))
            LoginFields(
                userName = viewModel.userName.collectAsState().value,
                password = viewModel.password.collectAsState().value,
                onUserNameChanged = viewModel::onUserNameChanged,
                onPasswordChanged = viewModel::onPasswordChanged,
                labelRowWidth = labelRowWidth.dp,
                isHorizontal = isHorizontal,
                modifier = Modifier.wrapContentWidth()
            )
            Spacer(Modifier.height(16.dp))
            ForgetPassword { }
            VerticalSpacer()
            LoginOrSignUp(
                modifier = if (isHorizontal)
                    Modifier.padding(start = 16.dp + labelRowWidth.dp).fillMaxWidth()
                else Modifier.padding(start = 16.dp).fillMaxWidth(),
                onLoginRequest = viewModel::onLoginRequest,
                onRegisterRequest = {}
            )

        }
    }


}


//
@Composable
private fun LoginOrSignUp(
    modifier: Modifier = Modifier,
    onLoginRequest: () -> Unit,
    onRegisterRequest: () -> Unit,
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.Start
    ) {

        Button(
            modifier = Modifier,
            onClick = onLoginRequest
        ) {
            Text(text = "Login".uppercase())
        }
        Spacer(Modifier.width(16.dp))
        Row(
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

    }

}

@Composable
private fun ForgetPassword(
    onPasswordResetRequest: () -> Unit,
) {
    Row(
        Modifier.fillMaxWidth(),
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