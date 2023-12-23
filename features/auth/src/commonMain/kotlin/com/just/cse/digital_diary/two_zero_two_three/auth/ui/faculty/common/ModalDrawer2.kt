package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.GroupSection
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup




@Composable
fun HomeScreenDrawerSheet(
    groups: List<ModalDrawerGroup>,
    onGroupClicked: (Int) -> Unit,
    onItemClicked: (groupIndex: Int, index: Int) -> Unit
) {
    var selected by remember {
        mutableStateOf<NavigationItem?>(null)
    }
    DrawerSheet(
        header = {
                 Text("This is Header")
        },
        footer = null,
        groups = groups,
        groupDecorator = { groupIndex ->
            groups.getOrNull(groupIndex)?.let { group ->
                GroupSection(
                    groupName = group.name,
                    onClick = { onGroupClicked(groupIndex) }
                )
            }
        },
        itemDecorator = { groupIndex, itemIndex ->
            groups.getOrNull(groupIndex)?.let { group ->
                if(group.isVisible){
                    group.members.getOrNull(itemIndex)?.let { navigationItem ->
                        NavigationDrawerItem(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            icon = {
                                Icon(
                                    navigationItem.unFocusedIcon,
                                    contentDescription = null
                                )
                            },
                            label = { Text(navigationItem.label) },
                            selected = navigationItem==selected,
                            onClick = {
                                onItemClicked(groupIndex, itemIndex)
                                selected=navigationItem
                            },
                        )

                    }
                }

            }


        })
}


@Composable
fun DrawerSheet(
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    groups: List<ModalDrawerGroup>,
    groupDecorator: @Composable (Int) -> Unit,
    itemDecorator: @Composable (groupIndex: Int, index: Int) -> Unit,
) {
   ModalDrawerSheet(
        modifier = Modifier
    ){
       LazyColumn(
           modifier = Modifier,
       ) {
           if (header != null) {
               item {
                   header()
               }
           }
           groups.forEachIndexed { groupIndex, group ->
               item {
                   groupDecorator(groupIndex)
               }
               itemsIndexed(group.members) { index, _ ->
                   itemDecorator(groupIndex, index)
               }
           }
           if (footer != null) {
               item {
                   footer()
               }
           }
       }

    }

}

@Composable
fun ModalDrawer(
    modifier: Modifier,
    drawerState: DrawerState,
    sheet: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = sheet,
        content = content,
    )
}