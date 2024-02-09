package com.just.cse.digital_diary.two_zero_two_three.search.navgraph.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.search.functionalities.searchable_employee_list.SearchableEmployeeList


object SearchFeatureNavGraph {
    private const val EMPLOYEES = "NoteListScreen"

    @Composable
    fun Graph(navController: NavHostController = rememberNavController()) {
        NavHost(
            navController = navController,
            startDestination = EMPLOYEES
        ) {
            composable(EMPLOYEES) {
              SearchableEmployeeList()
            }

        }


    }
}