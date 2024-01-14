package com.just.cse.digital_diary.two_zero_two_three.department.navigation

import androidx.compose.runtime.Composable

/*
This is only accessible from the outside module,it is the entry point to jump to this module
 */
@Composable
fun DepartmentModuleEntryPoint(
    departmentId: String,
    onExitRequested: () -> Unit,
) {
    DepartmentModuleLocalNavGraph(
        departmentId = departmentId,
        onExitRequested = onExitRequested
    )
}