package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import javax.swing.ImageIcon

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
    header: @Composable (ColumnScope.(Modifier) -> Unit) = {},
    onGroupClicked: (Int) -> Unit = {},
    content: @Composable () -> Unit,
) {
    ModalDrawer(
        modifier = Modifier,
        drawerGroups = destination,
        onDrawerItemClick = onDrawerItemClick,
        drawerState = drawerState,
        closeDrawer = closeDrawer,
        header = header,
        onGroupClicked = onGroupClicked,
        content = content,
    )

}

@Composable
fun ModalDrawer(
    modifier: Modifier,
    drawerGroups: List<ModalDrawerGroup>,
    onDrawerItemClick: (navigateTo: String) -> Unit,
    drawerState: DrawerState,
    closeDrawer: () -> Unit,
    header: @Composable (ColumnScope.(Modifier) -> Unit) = {},
    onGroupClicked: (Int) -> Unit = {},
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
                header = header,
                onGroupClicked = onGroupClicked
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
                GroupSection(groupName = group.name, onClick = {
                    onGroupClicked(index)
                })
                Divider(thickness = 2.dp)
                if (group.isVisible) {
                    GroupItem(
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
fun GroupItem(
    selectedItem: NavigationItem,
    destination: List<NavigationItem>,
    onNavigate: (String) -> Unit,
    closeDrawer: () -> Unit,
    onSelectedItem: (NavigationItem) -> Unit,
) {

    //placing the each drawer items
    destination.forEach { navigationItem ->
        NavigationDrawerItem(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            icon = {
                Icon(
                    imageVector =
                    navigationItem.unFocusedIcon,
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
fun GroupSection(
    modifier: Modifier = Modifier.fillMaxWidth(),
    icon: ImageVector = Icons.Default.Category,
    groupName: String,
    onClick: () -> Unit,
) {

    var backgroundBrush by remember {
        mutableStateOf(
            Brush.linearGradient(
                colors = listOf(
                    Color.Red,
                    Color.Blue
                )
            )
        )
    }

    fun changeBrush() {
        backgroundBrush = when (backgroundBrush) {
            Brush.linearGradient(colors = listOf(Color.Red, Color.Blue)) -> Brush.radialGradient(
                colors = listOf(Color.Green, Color.Yellow)
            )

            Brush.radialGradient(colors = listOf(Color.Green, Color.Yellow)) -> Brush.sweepGradient(
                colors = listOf(Color.Magenta, Color.Green)
            )

            else -> Brush.linearGradient(colors = listOf(Color.Red, Color.Blue))
        }
    }



    Surface(
        modifier = modifier
            .padding(top=2.dp, bottom = 2.dp)
            //   .background(animatedBrush)
            .clickable(onClick = { changeBrush() }),
        shadowElevation = 2.dp
    ) {
        Column {
            Row(
                Modifier.padding(start = 16.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(icon, null)
                Spacer(Modifier.width(4.dp))
                Text(text = groupName, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.weight(1f)) // Spacer at the beginnin
                IconButton(
                    onClick = onClick,
                    modifier = Modifier
                ) {
                    Icon(Icons.Default.ArrowDropDown, null)
                }
            }
        }

    }

}