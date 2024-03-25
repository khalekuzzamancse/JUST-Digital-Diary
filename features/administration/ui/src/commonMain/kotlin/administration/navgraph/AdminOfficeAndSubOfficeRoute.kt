package administration.navgraph

import administration.di.AdminOfficeComponentProvider
import administration.ui.OfficesAndSubOffices
import androidx.compose.runtime.Composable

@Composable
fun AdminOfficeAndSubOfficeRoute(
    onEmployeeListRequest: (String) -> Unit,
    onExitRequest: () -> Unit={},
    onBackButtonPress:@Composable (onBackButtonPress: () -> Boolean)->Unit={},
) {
    OfficesAndSubOffices(
        onEmployeeListRequest =onEmployeeListRequest,
        officeRepository = AdminOfficeComponentProvider.getAdminOfficeRepository(),
        subOfficeRepository = AdminOfficeComponentProvider.getAdminSubOfficeRepository(),
        backHandler=onBackButtonPress,
        onExitRequest = onExitRequest
    )
}