package administration.ui.public_

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val OFFICE_AND_SUB_OFFICES_SCREEN = "OfficeListScreen"
private const val SUB_OFFICE_ID = "subOfficeId"
private const val ADMIN_OFFICERS_LIST = "OfficersListScreen"
private const val OFFICERS_ROUTE = "$ADMIN_OFFICERS_LIST/{$SUB_OFFICE_ID}"

@Composable
fun AdministrationRoute(
    token:String?,
    navigationIcon: (@Composable () -> Unit)? = null,
    onEvent: (AdminEmployeeListEvent) -> Unit,
) {
    val navController = rememberNavController()

        NavHost(
            modifier = Modifier,
            navController = navController,
            startDestination = OFFICE_AND_SUB_OFFICES_SCREEN
        ) {
            composable(route = OFFICE_AND_SUB_OFFICES_SCREEN) {
                AdminOfficeAndSubOfficeRoute(
                    onEmployeeListRequest = { subOfficeId ->
                       try {
                           navController.navigate("$ADMIN_OFFICERS_LIST/$subOfficeId")
                       }
                       catch (_:Exception) {}
                    },
                    navigationIcon = navigationIcon,
                    onBackButtonPress = {},
                    token =token,

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
                    onEvent = onEvent,
                    token = token
                )
            }
        }




}
