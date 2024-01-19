package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.screen.DepartmentInfoScreen

/*
This is only the entry point of the faculty module
 */
@Composable
fun FacultyModuleEntryPoint(
    onExitRequested:()->Unit,
) {
    NavGraph(
        onExitRequested=onExitRequested
    )


}

