package com.just.cse.digital_diary.features.departments.navgraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home.HomeDestination
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.FacultyRepository

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
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Welcome to Departments  Information")
            }

        },

        )

}