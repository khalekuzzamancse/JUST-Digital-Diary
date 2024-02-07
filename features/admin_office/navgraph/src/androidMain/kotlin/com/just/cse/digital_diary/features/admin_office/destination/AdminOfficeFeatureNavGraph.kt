package com.just.cse.digital_diary.features.admin_office.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.just.cse.digital_diary.features.admin_office.destination.screens.AdminOfficeScreen
import com.just.cse.digital_diary.features.admin_office.destination.screens.AdminOfficersScreen

object AdminOfficeFeatureNavGraph {
    const val ROUTE = "AdminOfficeFeatureNavGraph"
    private const val OFFICE_SCREEN = "OfficeListScreen"
    private const val OFFICERS_SCREEN = "OfficersListScreen"
    fun graph(context:NavGraphBuilder,navController: NavController){
        with(context){
            navigation(
                route = ROUTE,
                startDestination =OFFICE_SCREEN
            ){
                composable(route = OFFICE_SCREEN) {
                    AdminOfficeScreen(
                        onExitRequest = {},
                        onEmployeeListRequest = { subOffice ->
                            navController.navigate(OFFICERS_SCREEN)
                        }
                    )
                }
                composable(route = OFFICERS_SCREEN) {
                    AdminOfficersScreen(
                        onExitRequest = {
                            navController.popBackStack()
                        },
                        subOfficeId = "01"
                    )
                }
            }

        }
    }

}
