package auth.ui.register.route

import common.newui.CustomSnackBarData

data class RegisterDestinationState(
    val isLoading: Boolean=false,
    val snackBarData: CustomSnackBarData?=null,
)
