package com.just.cse.digital_diary.features.faculty.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FacultyFeatureNavGraph() {
   val  navController: NavHostController = rememberNavController()
    var deptId: String = remember { "01" }
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = "FacultyListScreen"
    ) {
        composable(route = "FacultyListScreen") {
            FacultyListDestination(
                onExitRequest = {},
                onEmptyRequest = {
                    deptId=it
                    navController.navigate("TeacherListScreen")
                }
            )
        }
        composable(route = "TeacherListScreen") {
            TeacherListDestination(
                deptId = deptId,
                onExitRequest = {
                    navController.popBackStack()
                }
            )
        }
    }

}