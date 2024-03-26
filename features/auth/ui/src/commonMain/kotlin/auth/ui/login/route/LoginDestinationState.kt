package auth.ui.login.route

import common.ui.snackbar.SnackBarData

data class LoginDestinationState(
    val isLoading: Boolean=false,
    val message: String?=null,
    val snackBarData: SnackBarData?=null,
)