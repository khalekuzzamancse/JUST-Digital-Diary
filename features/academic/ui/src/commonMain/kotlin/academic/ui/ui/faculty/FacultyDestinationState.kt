package faculty.ui.faculty

import common.newui.CustomSnackBarData

data class FacultiesScreenState(
    val isLoading: Boolean = false,
    val snackBarData: CustomSnackBarData? = null,
    val openDepartmentListDestination: Boolean=false
)
