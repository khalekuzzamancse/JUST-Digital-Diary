package administration.navgraph

import administration.navgraph.event.AdminEvent
import administration.navgraph.screens.AdminOfficeAndSubOfficeRoute
import administration.navgraph.screens.AdminOfficersScreen
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


object AdminOfficeFeatureNavGraph {
    private const val OFFICE_AND_SUB_OFFICES_SCREEN = "OfficeListScreen"
    private const val SUB_OFFICE_ID="subOfficeId"
    private const val ADMIN_OFFICERS_LIST="OfficersListScreen"
    private const val OFFICERS_ROUTE = "$ADMIN_OFFICERS_LIST/{$SUB_OFFICE_ID}"




    @Composable
    fun NavHost(
        navController: NavHostController = rememberNavController(),
        onEvent: (AdminEvent) -> Unit, onBackPress: () -> Unit
    ) {

            NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = OFFICE_AND_SUB_OFFICES_SCREEN
            ) {
                composable(route = OFFICE_AND_SUB_OFFICES_SCREEN) {
                    AdminOfficeAndSubOfficeRoute(
                        onEmployeeListRequest = { subOfficeId ->
                            navController.navigate("$ADMIN_OFFICERS_LIST/$subOfficeId")
                        },
                        onBackButtonPress = { callback ->
                            BackHandler(onBack = {
                                val isConsumed = callback()
                                if (!isConsumed) onBackPress()
                            })

                        },
                        onExitRequest = {
                            onEvent(AdminEvent.ExitRequest)
                        }
                    )
                }
                composable(
                    route = OFFICERS_ROUTE,
                    arguments = listOf(navArgument(SUB_OFFICE_ID) { type = NavType.StringType })
                ) { backStackEntry ->
                    val subOffice = backStackEntry.arguments?.getString(SUB_OFFICE_ID)
                    AdminOfficersScreen(
                        onExitRequest = {
                            navController.popBackStack()
                        },
                        subOfficeId = subOffice ?: "",
                        onEvent = onEvent
                    )
                }
            }



    }

}
