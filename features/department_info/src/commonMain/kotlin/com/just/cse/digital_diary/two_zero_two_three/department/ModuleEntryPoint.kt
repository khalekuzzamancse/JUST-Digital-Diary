package com.just.cse.digital_diary.two_zero_two_three.department

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.department.navigation.DepartmentModuleLocalNavGraph
import com.just.cse.digital_diary.two_zero_two_three.department.theme.DepartmentInfoModuleTheme

/*
This is only accessible from the outside module,it is the entry point to jump to this module
 */
@Composable
fun DepartmentModuleEntryPoint(
    departmentId: String,
    onExitRequested: () -> Unit,
) {
    DepartmentInfoModuleTheme{
        DepartmentModuleLocalNavGraph(
            departmentId = departmentId,
            onExitRequested = onExitRequested
        )
    }

}