package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.runtime.Composable

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

