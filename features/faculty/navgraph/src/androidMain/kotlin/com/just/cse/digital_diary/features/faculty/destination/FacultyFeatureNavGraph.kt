package com.just.cse.digital_diary.features.faculty.destination

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.just.cse.digital_diary.features.faculty.destination.screens.FacultiesScreen
import com.just.cse.digital_diary.features.faculty.destination.screens.TeachersScreen

object FacultyFeatureNavGraph {
    const val ROUTE = "FacultyFeatureNavigation"
    private const val FACULTY_SCREEN = "FacultyListScreen"
    private const val TEACHERS_SCREEN = "TeacherListScreen"

    fun graph(context: NavGraphBuilder, navController: NavHostController) {
        with(context) {
            navigation(
                route = ROUTE,
                startDestination =FACULTY_SCREEN
            ) {
                composable(route = FACULTY_SCREEN) {
                    FacultiesScreen(
                        onTeacherListRequest = {
                            navController.navigate(TEACHERS_SCREEN)
                        },
                        backHandler = {onBackPress->
                            BackHandler(onBack = onBackPress)
                        }
                    )
                }
                composable(route = TEACHERS_SCREEN) {
                    TeachersScreen(
                        deptId = "01",
                        onExitRequest = {
                            navController.popBackStack()
                        }
                    )
                }
            }

        }

    }
}

