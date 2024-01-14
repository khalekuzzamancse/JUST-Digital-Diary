package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.HomeDestination
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.FacultyInfo

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
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Welcome to Faculty Information")
            }
        }
    )
}