package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.HomeContent
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.HomeDestination

@Composable
internal fun NavGraph(
    onExitRequested: () -> Unit,
) {
    val faculties = Faculties.facultyInfoList
    val navigator = LocalNavigator.current
    val navManager = NavigatorManager(navigator)
    HomeDestination(
        faculties = faculties,
        onExitRequested = onExitRequested,
        onFacultySelected = { facultyInfo ->
            navManager.navigateDepartmentsModule(facultyInfo.id)
        },
        onSearchRequested = {
            navManager.navigateSearchDestination()
        },
        content = {
            HomeContent()
        }
    )
}