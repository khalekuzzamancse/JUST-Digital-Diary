package com.just.cse.digital_diary.features.admin_office.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.features.admin_office.destination.destinations.AdminOfficeScreen
import com.just.cse.digital_diary.features.admin_office.destination.destinations.AdminOfficerList

@Composable
fun AdminOfficeFeatureNavGraph() {
   val  navController: NavHostController = rememberNavController()
    var subOfficeId: String = remember { "" }
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = "OfficeListScreen"
    ) {
        composable(route = "OfficeListScreen") {
            AdminOfficeScreen(
                onExitRequest = {},
                onEmployeeListRequest = { subOffice ->
                    subOfficeId = subOffice
                    navController.navigate("AdminOfficerListScreen")
                }
            )
        }
        composable(route = "AdminOfficerListScreen") {
            AdminOfficerList(
                onExitRequest = {
                    navController.popBackStack()
                },
                subOfficeId = subOfficeId
            )
        }
    }

}
