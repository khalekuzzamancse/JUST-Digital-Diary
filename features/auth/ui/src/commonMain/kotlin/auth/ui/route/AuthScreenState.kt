package auth.ui.route

import common.ui.snackbar.SnackBarData

data class AuthScreenState(
    val showProgressBar: Boolean=false,
    val snackBarMessage: String?=null,
    val showRegisterForm: Boolean=false,
    val snackBarData: SnackBarData?=null,
)
