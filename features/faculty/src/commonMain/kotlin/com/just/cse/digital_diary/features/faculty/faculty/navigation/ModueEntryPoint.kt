package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.HomeDestination

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

