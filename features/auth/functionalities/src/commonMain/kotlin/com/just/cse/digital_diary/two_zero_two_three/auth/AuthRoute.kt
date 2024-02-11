package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.events.RegisterControlEvents
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.login.destination.LoginDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.login.viewmodel.LoginDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.register.RegisterDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.register.viewmodel.RegisterDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.auth.state.AuthScreenState
import com.just.cse.digital_diary.two_zero_two_three.auth.viewmodel.AuthViewModel
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.domain.register.repository.RegisterRepository
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory.LoginRepository
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event.LoginEvent
import kotlinx.coroutines.launch


@Composable
fun AuthRoute(
    loginRepository: LoginRepository,
    registrationRepository: RegisterRepository,
    onLoginSuccess: (userName: String, password: String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    val authViewModel = remember{
        AuthViewModel(
            loginRepository = loginRepository,
            registrationRepository = registrationRepository
        )
    }
    val state = authViewModel.uiState.collectAsState().value
    Scaffold(
        modifier=Modifier,
        topBar = {
            if (state.showRegisterForm){
                IconButton(onClick = {
                    authViewModel.closeRegistrationDestination()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "BackArrow"
                    )
                }
            }
            else{
                Spacer(Modifier.height(8.dp))
            }

        }
    ) { scaffoldPadding ->
        AuthRoute(
            modifier = Modifier.padding(scaffoldPadding),
            authViewModel=authViewModel,
            onLoginSuccess = onLoginSuccess,
            backHandler = backHandler
        )
    }


}
/**
 * * This composable is only accessible within this module
 * * It serves as the container for the Login[LoginDestination] and Register[RegisterDestination] Destinations.
 * * Client don't have direct access to the Login[LoginDestination] and Register[RegisterDestination]
 * * Initially it Open the Login[LoginDestination]
 * * Upon a Register request, the Register Destination [RegisterDestination] becomes visible.
 * * In compact Window Register [RegisterDestination] destination will be pop up top of Login[LoginDestination] destination
 * * In Medium and Expanded Window Login[LoginDestination] and Register [RegisterDestination] destination are displayed side by side
 * @param modifier [Modifier] (mandatory) controls size and other behaviors of the Auth Screen
 * @param onLoginSuccess (mandatory) will be called when login is successful
 */
@Composable
private fun AuthRoute(
    modifier: Modifier=Modifier,
    authViewModel:AuthViewModel,
    onLoginSuccess: (userName: String, password: String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val state = authViewModel.uiState.collectAsState().value
        AuthRoute(
            modifier =modifier,
            backHandler = backHandler,
            onCloseRegisterFormRequest = {
                authViewModel.closeRegistrationDestination()
            },
            state = state,
            loginViewModel = authViewModel.loginViewModel,
            registerViewModel = authViewModel.registerViewModel,
            onLoginEvent = { event ->
                when (event) {
                    LoginEvent.LoginControlsEvent.RegisterRequest -> {
                        authViewModel.openRegisterDestination()
                    }

                    LoginEvent.LoginControlsEvent.LoginRequest -> {
                        scope.launch {
                            val responseModel = authViewModel.login()
                            if (responseModel != null)
                                onLoginSuccess(responseModel.username, responseModel.password)

                        }

                    }
                }
            },
            onRegisterEvent = { event ->
                when (event) {
                    RegisterControlEvents.RegisterRequest -> {
                        scope.launch {
                            val success = authViewModel.register()
                            if (success) {
                                authViewModel.closeRegistrationDestination()
                            }
                        }

                    }
                }
            },

            )


}

@Composable
private fun AuthRoute(
    modifier: Modifier = Modifier,
    state: AuthScreenState,
    loginViewModel: LoginDestinationViewModel,
    registerViewModel: RegisterDestinationViewModel,
    onLoginEvent: (LoginEvent) -> Unit,
    onRegisterEvent: (RegisterControlEvents) -> Unit = {},
    onCloseRegisterFormRequest: () -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit
) {
    backHandler {
        if (state.showRegisterForm)
            onCloseRegisterFormRequest()
    }
    TwoPaneLayout(
        modifier = modifier,
        showProgressBar = state.showProgressBar,
        snackBarMessage = state.snackBarMessage,
        showTopOrRightPane = state.showRegisterForm,
        leftPane = {
            LoginDestination(
                modifier = Modifier.padding(8.dp)
                    .widthIn(max = 700.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
//                    .background(Color.Blue)
                ,
                viewModel = loginViewModel,
                onEvent = onLoginEvent
            )
        },
        topOrRightPane = {
            RegisterDestination(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
//                    .background(Color.Red)
                ,
                viewModel = registerViewModel,
                onEvent = onRegisterEvent
            )
        },
        secondaryPaneAnimationState = state.showRegisterForm
    )
}



