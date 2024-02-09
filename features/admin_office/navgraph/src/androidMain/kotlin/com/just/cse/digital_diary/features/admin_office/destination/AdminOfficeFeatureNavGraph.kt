package com.just.cse.digital_diary.features.admin_office.destination

import androidx.activity.compose.BackHandler
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.just.cse.digital_diary.features.admin_office.destination.screens.AdminOfficeAndSubOfficeRoute
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
                    AdminOfficeAndSubOfficeRoute(
                        onEmployeeListRequest = { subOffice ->
                            navController.navigate(OFFICERS_SCREEN)
                        },
                        onBackButtonPress = {onBackPress ->
                            BackHandler(onBack = onBackPress)

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
