package administration.navgraph

import administration.di.AdminOfficeComponentProvider
import administration.ui.officers.employeelist.components.AdminEmployeeListEvent
import administration.ui.officers.employeelist.route.AdminOfficers
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.TopBarDecoratorCommon

@Composable
fun AdminOfficersScreen(
    subOfficeId: String,
    onExitRequest:()->Unit,
    onEvent:(AdminEmployeeListEvent)->Unit,
) {
    TopBarDecoratorCommon(
        topNavigationIcon = Icons.AutoMirrored.Default.ArrowBack,
        onNavigationIconClick = onExitRequest,
        topBarTitle = "Admin Officers"
    ) {
        AdminOfficers(
            modifier = Modifier.padding(it),
            subOfficeId=subOfficeId,
            repository = AdminOfficeComponentProvider.getAdminOfficersListRepository(),
            onEvent=onEvent
        )
    }

}
