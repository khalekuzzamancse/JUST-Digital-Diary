package com.just.cse.digital_diary.two_zero_two_three.department.navigation

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen.Screen


//
/*
All department have the shared top and bottom bar.
they must maintain a separate backstack means navigator

 */


//prevent outside module to access it

@Composable
internal fun DepartmentModuleLocalNavGraph(
    departmentId: String,
    onExitRequested: () -> Unit,
) {

    Screen(
        departmentId=departmentId,
        onExitRequested=onExitRequested
    )


}


