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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextFieldProperties
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextFieldState
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.MyDropDownMenu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object RegistrationFormLabels {
    const val FULL_NAME = "Full Name"
    const val EMAIL = "Email"
    const val USER_NAME = "User Name"
    const val PASSWORD = "Password"
    const val CONFIRMED_PASSWORD = "Confirm Password"

    // ... (other existing code)

    fun getRowWidth(measurer: TextMeasurer): Int {
        val fullNameLabelWidth = measurer.measure(FULL_NAME).size.width
        val emailLabelWidth = measurer.measure(EMAIL).size.width
        val userNameLabelWidth = measurer.measure(USER_NAME).size.width
        val passwordLabelWidth = measurer.measure(PASSWORD).size.width
        val confirmedPasswordLabelWidth = measurer.measure(CONFIRMED_PASSWORD).size.width
//
//        println("fullNameLabelWidth:$fullNameLabelWidth, emailLabelWidth:$emailLabelWidth, " +
//                "userNameLabelWidth:$userNameLabelWidth, passwordLabelWidth:$passwordLabelWidth, " +
//                "confirmedPasswordLabelWidth:$confirmedPasswordLabelWidth")

        return maxOf(
            fullNameLabelWidth,
            emailLabelWidth,
            userNameLabelWidth,
            passwordLabelWidth,
            confirmedPasswordLabelWidth
        )
    }
}

class RegistrationViewModel() {
    //FULL NAME
    private val _fullName = MutableStateFlow(FormTextFieldState(value = ""))
    val fullName = _fullName.asStateFlow()
    fun onFullNameChanged(fullName: String) {
        _fullName.update { it.copy(value = fullName) }
    }

    //Email
    private val _email = MutableStateFlow(FormTextFieldState(value = ""))
    val email = _email.asStateFlow()
    fun onEmailChanged(email: String) {
        _email.update { it.copy(value = email) }
    }

    //UserName
    private val _userName = MutableStateFlow(FormTextFieldState(value = ""))
    val userName = _userName.asStateFlow()
    fun onUsernameChanged(username: String) {
        _userName.update { it.copy(value = username) }
    }

    //
    private val _dept = MutableStateFlow(FormTextFieldState(value = ""))
    val dept = _dept.asStateFlow()
    fun onDeptChanged(dept: String) {
        _dept.update { it.copy(value = dept) }
    }

    //Password
    private val _password = MutableStateFlow(FormTextFieldState())
    val password = _password.asStateFlow()
    fun onPasswordChanged(password: String) {
        _password.update { it.copy(value = password) }
    }

    //ConfirmPassword
    private val _confirmedPassword = MutableStateFlow(FormTextFieldState())
    val confirmedPassword = _confirmedPassword.asStateFlow()
    fun onConfirmedPasswordChanged(confirmedPassword: String) {
        _confirmedPassword.update { it.copy(value = confirmedPassword) }
    }


    fun onLoginRequest() {
        // println("onLoginRequest, ${userName.value}, ${password.value}")
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun RegistrationForm(
    modifier: Modifier = Modifier,
) {
    val viewModel = remember {
        RegistrationViewModel()
    }
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

@Composable
fun RegistrationFields(
    modifier: Modifier = Modifier,
    name: FormTextFieldState,
    onNameChanged: (String) -> Unit,
    email: FormTextFieldState,
    onEmailChanged: (String) -> Unit,
    username: FormTextFieldState,
    onUserNameChanged: (String) -> Unit,
    password: FormTextFieldState,
    onPasswordChanged: (String) -> Unit,
    dept: FormTextFieldState,
    onDeptChanged: (String) -> Unit,
    confirmedPassword: FormTextFieldState,
    onConfirmedPassword: (String) -> Unit,
    labelRowWidth: Dp,
    isHorizontal: Boolean,
) {
    var passwordFieldProperty by remember {
        mutableStateOf(
            FormTextFieldProperties(
                label = RegistrationFormLabels.PASSWORD,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = Icons.Default.Lock,
                trailingIcon = Icons.Default.Visibility,
                keyboardType = KeyboardType.Password
            )
        )
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    val togglePasswordVisibility: () -> Unit = {
        showPassword = !showPassword
        passwordFieldProperty = if (showPassword) {
            passwordFieldProperty.copy(
                trailingIcon = Icons.Default.VisibilityOff,
                visualTransformation = VisualTransformation.None
            )
        } else {
            passwordFieldProperty.copy(
                trailingIcon = Icons.Default.Visibility,
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }

    Column(modifier = modifier) {
        AuthTextField(
            properties = FormTextFieldProperties(
                label = RegistrationFormLabels.FULL_NAME,
                leadingIcon = Icons.Default.Person,
                keyboardType = KeyboardType.Text
            ),
            state = name,
            onValueChanged = onNameChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth
        )
        Spacer(Modifier.height(8.dp))
        AuthTextField(
            properties = FormTextFieldProperties(
                label = RegistrationFormLabels.EMAIL,
                leadingIcon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            ),
            state = email,
            onValueChanged = onEmailChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth
        )
        Spacer(Modifier.height(8.dp))

        AuthTextField(
            properties = FormTextFieldProperties(
                label = RegistrationFormLabels.USER_NAME,
                leadingIcon = Icons.Default.Person,
                keyboardType = KeyboardType.Text
            ),
            state = username,
            onValueChanged = onUserNameChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth

        )
        Spacer(Modifier.height(8.dp))
        MyDropDownMenu(
            options = listOf("CSE","EEE"),
            onOptionSelected = onDeptChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth =labelRowWidth,
            selected = dept.value,
            leadingIcon = Icons.Default.School
        )
        Spacer(Modifier.height(8.dp))

        AuthTextField(
            properties = passwordFieldProperty,
            state = password,
            onValueChanged = onPasswordChanged,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth,
            onTrailingIconClick = togglePasswordVisibility
        )
        Spacer(Modifier.height(8.dp))
        AuthTextField(
            properties = passwordFieldProperty.copy(
                label = RegistrationFormLabels.CONFIRMED_PASSWORD
            ),
            state = confirmedPassword,
            onValueChanged = onConfirmedPassword,
            isHorizontalOrientation = isHorizontal,
            labelMinWidth = labelRowWidth,
            onTrailingIconClick = togglePasswordVisibility
        )
        Spacer(Modifier.height(8.dp))
    }


}