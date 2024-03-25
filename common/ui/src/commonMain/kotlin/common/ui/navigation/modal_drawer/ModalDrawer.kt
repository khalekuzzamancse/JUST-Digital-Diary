package common.ui.navigation.modal_drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier






@Composable
fun ModalDrawer(
    modifier: Modifier,
    drawerState: DrawerState,
    sheet: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
        ModalNavigationDrawer(
            gesturesEnabled = true,
            modifier = modifier,
            drawerState = drawerState,
            drawerContent = sheet,
            content = content,
        )


}
