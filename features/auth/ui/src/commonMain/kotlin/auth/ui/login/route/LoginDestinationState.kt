package auth.ui.login.route

import common.newui.CustomSnackBarData

data class LoginDestinationState(
    val isLoading: Boolean = false,
    val snackBarData: CustomSnackBarData? = null,
)