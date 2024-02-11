package com.just.cse.digital_diary.features.admin_office.destination

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.features.admin_office.destination.event.AdminEvent
import com.just.cse.digital_diary.features.admin_office.destination.screens.AdminOfficeAndSubOfficeRoute
import com.just.cse.digital_diary.features.admin_office.destination.screens.AdminOfficersScreen

object AdminOfficeFeatureNavGraph {
    private const val OFFICE_AND_SUB_OFFICES_SCREEN = "OfficeListScreen"
    private const val OFFICERS_SCREEN = "OfficersListScreen"

    @Composable
    fun Graph(navController: NavHostController= rememberNavController(),onEvent:(AdminEvent)->Unit) {
        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = OFFICE_AND_SUB_OFFICES_SCREEN
        ){
            composable(route = OFFICE_AND_SUB_OFFICES_SCREEN) {
                AdminOfficeAndSubOfficeRoute(
                    onEmployeeListRequest = { subOffice ->
                        navController.navigate(OFFICERS_SCREEN)
                    },
                    onBackButtonPress = {onBackPress ->
                        BackHandler(onBack = onBackPress)

                    },
                    onExitRequest = {
                        onEvent(AdminEvent.ExitRequest)
                    }
                )
            }
            composable(route = OFFICERS_SCREEN) {
                AdminOfficersScreen(
                    onExitRequest = {
                        navController.popBackStack()
                    },
                    subOfficeId = "01",
                    onEvent = onEvent
                )
            }
        }

    }

}
