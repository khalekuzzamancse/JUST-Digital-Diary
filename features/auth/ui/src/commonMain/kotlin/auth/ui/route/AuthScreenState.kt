package auth.ui.route

import common.newui.CustomSnackBarData
import common.ui.snackbar.SnackBarData

data class AuthScreenState(
    val showProgressBar: Boolean=false,
    val showRegisterForm: Boolean=false,
    val snackBarData: CustomSnackBarData?=null,
)
