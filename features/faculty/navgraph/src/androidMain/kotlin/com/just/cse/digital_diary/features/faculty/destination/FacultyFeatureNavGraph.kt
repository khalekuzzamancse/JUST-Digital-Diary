package com.just.cse.digital_diary.features.faculty.destination

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.just.cse.digital_diary.features.faculty.components.event.FacultyEvent
import com.just.cse.digital_diary.features.faculty.destination.event.FacultyFeatureEvent
import com.just.cse.digital_diary.features.faculty.destination.screens.FacultiesScreen
import com.just.cse.digital_diary.features.faculty.destination.screens.TeachersScreen

object FacultyFeatureNavGraph {
    private const val DEPT_ID = "deptId"
    private const val FACULTY_SCREEN = "FacultyListScreen"
    private const val TEACHER_LIST="TeacherListScreen"
    private const val TEACHERS_ROUTE = "$TEACHER_LIST/{$DEPT_ID}"



    @Composable
    fun NavHost(
        navController: NavHostController = rememberNavController(),
        onBackPressed: () -> Unit,
        onEvent: (FacultyFeatureEvent) -> Unit
    ) {
        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = FACULTY_SCREEN
        ) {

            composable(route = FACULTY_SCREEN) {
                FacultiesScreen(
                    onTeacherListRequest = {id->
                        navController.navigate("$TEACHER_LIST/$id")
                    },
                    backHandler = { onBackPress ->
                        //overriding backhander will prevent it parent to notify that back button is
                        //pressed as a result the parent nav controller may not notify that back button is pressed
                        //as a result the parent nav controller may unable to pop destination automatically
                        BackHandler(
                            onBack = {
                                val isConsumed = onBackPress()
                                if (!isConsumed) {
                                    onBackPressed()
                                }
                            },
                        )
                    },
                    onExitRequest = {
                        onEvent(FacultyFeatureEvent.ExitRequest)
                    }
                )
            }
            composable(
                route = TEACHERS_ROUTE,
                arguments = listOf(navArgument(DEPT_ID) { type = NavType.StringType })
            ) { backStackEntry ->
                val deptID = backStackEntry.arguments?.getString(DEPT_ID)
                TeachersScreen(
                    deptId = deptID ?: "",
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
