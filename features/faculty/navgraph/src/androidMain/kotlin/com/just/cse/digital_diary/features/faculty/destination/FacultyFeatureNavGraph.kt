package com.just.cse.digital_diary.features.faculty.destination

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.features.faculty.components.event.FacultyEvent
import com.just.cse.digital_diary.features.faculty.destination.event.FacultyFeatureEvent
import com.just.cse.digital_diary.features.faculty.destination.screens.FacultiesScreen
import com.just.cse.digital_diary.features.faculty.destination.screens.TeachersScreen
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.event.TeacherListEvent

object FacultyFeatureNavGraph {
    private const val FACULTY_SCREEN = "FacultyListScreen"
    private const val TEACHERS_SCREEN = "TeacherListScreen"

    @Composable
    fun Graph(
        navController: NavHostController = rememberNavController(),
        onEvent: (FacultyFeatureEvent) -> Unit
    ) {
        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = FACULTY_SCREEN
        ) {
            composable(route = FACULTY_SCREEN) {
                FacultiesScreen(
                    onTeacherListRequest = {
                        navController.navigate(TEACHERS_SCREEN)
                    },
                    backHandler = { onBackPress ->
                        BackHandler(onBack = onBackPress)
                    },
                    onExitRequest = {
                        onEvent(FacultyFeatureEvent.ExitRequest)
                    }
                )
            }
            composable(route = TEACHERS_SCREEN) {
                TeachersScreen(
                    deptId = "01",
                    onExitRequest = {
                        navController.popBackStack()
                    },
                    onEvent = { event ->
                        toEvent(event)?.let { onEvent(it) }
                    }
                )
            }
        }


    }
}

private fun toEvent(event: FacultyEvent): FacultyFeatureEvent? {
    val ev: FacultyFeatureEvent? = when (event) {
        is FacultyEvent.CallRequest -> FacultyFeatureEvent.CallRequest(event.number)
        is FacultyEvent.MessageRequest -> FacultyFeatureEvent.MessageRequest(
            event.number
        )

        is FacultyEvent.EmailRequest -> FacultyFeatureEvent.EmailRequest(event.email)
        else -> {
            null
        }
    }
    return ev
}
