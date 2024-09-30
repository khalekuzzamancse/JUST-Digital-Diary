package administration.ui.public_

import administration.factory.UiFactory
import administration.ui.common.SnackNProgressBarDecorator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
    onEvent: (AdminEmployeeListEvent) -> Unit,
    onMenuIconClick: () -> Unit
) {
    val navController = rememberNavController()
    val viewModel = remember {
        OfficeScreenViewModel(
            officeController = UiFactory.createOfficerController(token),
            subOfficeController = UiFactory.createSubOfficeController(token)
        )
    }
    SnackNProgressBarDecorator(
        isLoading = viewModel.isLoading.collectAsState(false).value,
        snackBarMessage = viewModel.screenMessage.collectAsState(null).value,
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
                    onBackButtonPress = {


                    },
                    onExitRequest = onMenuIconClick,
                    token =token,
                    viewModel = viewModel
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


}
