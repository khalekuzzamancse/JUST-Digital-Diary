package com.just.cse.digital_diary.two_zero_two_three.auth.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldProperties
import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldState
import com.just.cse.digital_diary.features.common_ui.form.MyDropDownMenu
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.InputTextField
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


object RegistrationFormLabels {
    const val FULL_NAME = "Full Name"
    const val EMAIL = "Email"
    const val USER_NAME = "User Name"
    const val PASSWORD = "Password"
    const val CONFIRMED_PASSWORD = "Confirm Password"



    fun getRowWidth(measurer: TextMeasurer): Int {
        val fullNameLabelWidth = measurer.measure(FULL_NAME).size.width
        val emailLabelWidth = measurer.measure(EMAIL).size.width
        val userNameLabelWidth = measurer.measure(USER_NAME).size.width
        val passwordLabelWidth = measurer.measure(PASSWORD).size.width
        val confirmedPasswordLabelWidth = measurer.measure(CONFIRMED_PASSWORD).size.width
        return maxOf(
            fullNameLabelWidth,
            emailLabelWidth,
            userNameLabelWidth,
            passwordLabelWidth,
            confirmedPasswordLabelWidth
        )
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
    viewModel:RegistrationViewModel,
) {
    val density = LocalDensity.current.density
    val textMeasurer = rememberTextMeasurer()
    val labelRowWidth: Int = remember {
        val res = RegistrationFormLabels.getRowWidth(textMeasurer)
        (res * density).toInt() + 80//fixing bug adding 80
        //   has a bug when use material theme ,fix it

    }


    val isHorizontal = calculateWindowSizeClass().widthSizeClass != WindowWidthSizeClass.Compact

    val w = calculateWindowSizeClass().widthSizeClass

    Row(
        modifier.wrapContentWidth(),
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
            LoginSectionHeader(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(16.dp))
            RegistrationFields(
                name = viewModel.fullName.collectAsState().value,
                onNameChanged = viewModel::onFullNameChanged,
                username = viewModel.userName.collectAsState().value,
                onUserNameChanged = viewModel::onUsernameChanged,
                email = viewModel.email.collectAsState().value,
                onEmailChanged = viewModel::onEmailChanged,
                dept = viewModel.dept.collectAsState().value,
                onDeptChanged = viewModel::onDeptChanged,
                password = viewModel.password.collectAsState().value,
                onPasswordChanged = viewModel::onPasswordChanged,
                confirmedPassword = viewModel.confirmedPassword.collectAsState().value,
                onConfirmedPassword = viewModel::onConfirmedPasswordChanged,
                labelRowWidth = labelRowWidth.dp,
                isHorizontal = isHorizontal,
                modifier = Modifier.wrapContentWidth()
            )
            Spacer(Modifier.height(16.dp))


        }
    }

}




