package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.top_most_home_destination.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer.ModalDrawerState
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer.ModalDrawer
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer.NavGroupSelectedItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.top_most_home_destination.drawer.sheet.Sheet


@Composable
fun ScreenDrawer(
    navigationGroups: List<NavigationGroup>,
    selectedItem: NavGroupSelectedItem,
    onNavigationGroupClicked: (Int) -> Unit = {},
    onNavigationItemClick: (groupIndex: Int, itemIndex: Int) -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val drawerController by remember {
        mutableStateOf(ModalDrawerState(scope))
    }
    ModalDrawer(
        modifier = Modifier,
        drawerState = drawerController.drawerState,
        sheet = {
            Sheet(
                selectedItemIndex = selectedItem,
                groups = navigationGroups,
                onGroupClicked = onNavigationGroupClicked,
                onItemClicked = { groupIndex, index ->
                    onNavigationItemClick(groupIndex, index)
                    drawerController.closeDrawer()
                }
            )
        },
        content = content,
    )
}

