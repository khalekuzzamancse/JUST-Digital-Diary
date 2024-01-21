package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.faculties.FacultiesScreen

@Composable
internal fun NavGraph(
    onExitRequested: () -> Unit,
) {
    val navigator= LocalNavigator.current
    val navigatorManager= remember { NavigatorManager(navigator) }
    FacultiesScreen(
        onDepartmentDepartmentSelected = {
            navigatorManager.navigateDepartmentInfoModule(it)

        },
        onExitRequest=onExitRequested
    )
}

