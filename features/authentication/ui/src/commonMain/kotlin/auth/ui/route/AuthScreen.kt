package auth.ui.route

import androidx.compose.runtime.Composable

/**
 * * This is the Only Entry and Exit point to the AuthModule.
 * * This Composable delegate to to AuthScreen [AuthRoute]
 * @param onLoginSuccess (mandatory) will be called when login is successful
 * @param onExitRequest(mandatory) will be called when want to exit from the AuthModule
 */

@Composable
internal fun AuthRoute(
    onLoginSuccess:(String,String)->Unit,
    onExitRequest:()->Unit={},
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
//        AuthRoute(
//            loginRepository = AuthComponentProvider.getLoginRepository(),
//            registrationRepository = AuthComponentProvider.getRegisterRepository(),
//            onLoginSuccess =onLoginSuccess,
//            backHandler=backHandler
//        )



}

