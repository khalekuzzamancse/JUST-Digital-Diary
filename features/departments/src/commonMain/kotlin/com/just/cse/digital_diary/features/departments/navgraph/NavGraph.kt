package com.just.cse.digital_diary.features.departments.navgraph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home.HomeContent
import com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home.HomeDestination
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.FacultyRepository

@Composable
fun NavGraph(
    facultyId: String,
    onExitRequest: () -> Unit,
) {
    val navigator = LocalNavigator.current
    val navManager = NavigatorManager(navigator)
    val departments = FacultyRepository.getDepartments(facultyId)
    HomeDestination(
        departments = departments,
        onExitRequest = onExitRequest,
        onSearchRequested = {
            navManager.navigateSearchDestination()
        },
        onDepartmentSelected = { department ->
              navManager.navigateDepartmentInfoModule(department.id)
        },
        content = {
            HomeContent()
        },

        )

}