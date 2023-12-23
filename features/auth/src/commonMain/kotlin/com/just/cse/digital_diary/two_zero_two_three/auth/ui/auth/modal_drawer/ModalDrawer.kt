package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem

data class ModalDrawerGroup(
    val name: String,
    val members: List<NavigationItem>,
    val isVisible: Boolean = true,
)

@Composable
fun ScreenWithDrawer(
    drawerState: DrawerState,
    destination: List<ModalDrawerGroup>,
    closeDrawer: () -> Unit,
    onDrawerItemClick: (String) -> Unit,
    header: @Composable (ColumnScope.(Modifier) -> Unit)={},
    onGroupClicked:(Int)->Unit={},
    content: @Composable () -> Unit,
) {
    ModalDrawer(
        modifier = Modifier,
        drawerGroups = destination,
        onDrawerItemClick = onDrawerItemClick,
        drawerState = drawerState,
        closeDrawer = closeDrawer,
        content = content,
        header=header,
        onGroupClicked=onGroupClicked
    )

}

@Composable
fun ModalDrawer(
    modifier: Modifier,
    drawerGroups: List<ModalDrawerGroup>,
    onDrawerItemClick: (navigateTo: String) -> Unit,
    drawerState: DrawerState,
    closeDrawer: () -> Unit,
    header: @Composable (ColumnScope.(Modifier) -> Unit)={},
    onGroupClicked:(Int)->Unit={},
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            DrawerSheetContent(
                modifier = Modifier.fillMaxHeight(),
                drawerGroups,
                onNavigate = onDrawerItemClick,
                closeDrawer = closeDrawer,
                header=header,
                onGroupClicked=onGroupClicked
            )
        },
        content = content,
    )
}

@Composable
private fun DrawerSheetContent(
    modifier: Modifier = Modifier,
    drawerGroups: List<ModalDrawerGroup>,
    onNavigate: (navigateTo: String) -> Unit,
    closeDrawer: () -> Unit,
    header: @Composable (ColumnScope.(Modifier) -> Unit) = {},
    onGroupClicked: (Int) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    ModalDrawerSheet(
        modifier = modifier.verticalScroll(scrollState)
    ) {

        header(Modifier)

        if (drawerGroups.isNotEmpty()) {
            val firstGroupFirstItem = drawerGroups[0].members[0]
            val selectedItem = remember { mutableStateOf(firstGroupFirstItem) }

            drawerGroups.forEachIndexed { index, group ->
                val items = group.members
                GroupSection(groupName=group.name,onClick={
                    onGroupClicked(index)
                })
                Divider(thickness = 2.dp)
                if(group.isVisible){
                    GroupItems(
                        destination = items,
                        selectedItem = selectedItem.value,
                        onSelectedItem = {
                            selectedItem.value = it
                        },
                        closeDrawer = closeDrawer,
                        onNavigate = onNavigate,
                    )
                }

            }
        } else {
            // Handle the case where drawerGroups is empty
            Text("No groups available", modifier = Modifier.padding(16.dp))
        }
    }
}


 @Composable
fun GroupItems(
    selectedItem:NavigationItem,
    destination: List<NavigationItem>,
    onNavigate:(String) -> Unit,
    closeDrawer: () -> Unit,
    onSelectedItem:(NavigationItem)->Unit,
) {

    //placing the each drawer items
    destination.forEach { navigationItem ->
        NavigationDrawerItem(
            modifier = Modifier.padding(top=8.dp, bottom = 8.dp),
            icon = {
                Icon(
                    imageVector = if (navigationItem == selectedItem)
                        navigationItem.focusedIcon
                    else navigationItem.unFocusedIcon,
                    contentDescription = null
                )
            },
            label = { Text(navigationItem.label) },
            selected = navigationItem == selectedItem,
            onClick = {
                onSelectedItem(navigationItem)
                onNavigate(navigationItem.label)
                closeDrawer()
            },

        )
    }
}


@Composable
private fun GroupSection(
    groupName: String,
    onClick:()->Unit,
) {
    Divider(thickness = 2.dp)
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
        IconButton(
            onClick =onClick,
            modifier = Modifier.align(Alignment.CenterVertically)
        ){
            Icon(Icons.Default.ArrowDropDown,null)
        }
    }
}