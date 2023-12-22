package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.HomeDrawerItems
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem

data class ModalDrawerGroup(
    val name: String,
    val members: List<NavigationItem>,
)

@Composable
fun ScreenWithDrawer(
    drawerWidth: Dp,
    drawerState: DrawerState,
    destination:List<ModalDrawerGroup>,
    closeDrawer: () -> Unit,
    onDrawerItemClick: (String) -> Unit,
    content: @Composable () -> Unit,
) {
    ModalDrawer(
        modifier = Modifier,
        drawerGroups = destination,
        onDrawerItemClick = onDrawerItemClick,
        closeDrawer = closeDrawer,
        drawerState = drawerState,
        drawerWidth = drawerWidth
    ) {
        content()
    }

}

@Composable
fun ModalDrawer(
    modifier: Modifier,
    drawerWidth: Dp,
    drawerGroups: List<ModalDrawerGroup>,
    onDrawerItemClick: (navigateTo: String) -> Unit,
    drawerState: DrawerState,
    closeDrawer: () -> Unit,
    content: @Composable () -> Unit,
    ) {
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                modifier = Modifier
                    .width(drawerWidth)
                    .fillMaxHeight(),
                drawerGroups,
                onNavigate = onDrawerItemClick,
                closeDrawer = closeDrawer,
            )
        }) {
        content()
    }
}

@Composable
private fun DrawerContent(
    modifier: Modifier = Modifier,
    drawerGroups: List<ModalDrawerGroup>,
    onNavigate: (navigateTo: String) -> Unit,
    closeDrawer: () -> Unit,
) {
    val scrollState = rememberScrollState()
    ModalDrawerSheet(
        modifier = modifier.verticalScroll(scrollState)
    ) {

        val firstGroupFirstItem = drawerGroups[0].members[0]
        val selectedItem = remember { mutableStateOf(firstGroupFirstItem) }
        drawerGroups.forEach { group ->
            val items = group.members
            DisplayGroupName(group.name)

            //placing the each drawer items
            items.forEach { drawerItem ->
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = drawerItem.icon,
                            contentDescription = null
                        )
                    },
                    label = { Text(drawerItem.label) },
                    selected = drawerItem == selectedItem.value,
                    onClick = {
                        selectedItem.value = drawerItem
                        onNavigate(drawerItem.label)
                        closeDrawer()
                    },
                )
            }
        }


    }

}


@Composable
private fun DisplayGroupName(groupName: String) {
    Row(
        //We want to align the start of the group name
        //with the start of the NavigationDrawer icon
        //that is why we have to use the exact padding
        //and the layout that is used by the  NavigationDrawer()
        //compose,so take the padding value and the alignmentValue
        //from the  NavigationDrawer() composable
        Modifier.padding(start = 16.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = groupName)
    }
}