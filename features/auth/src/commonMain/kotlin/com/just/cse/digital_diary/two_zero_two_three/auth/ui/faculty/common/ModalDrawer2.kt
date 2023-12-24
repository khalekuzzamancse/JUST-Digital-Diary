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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.GroupSection
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup



/*
Passing clicked group,
passing clicked item.
do don't need to pass group item group index when passing clicked item index
because parent already know which group is selected/clicked.
But what if the case,if all group item are shown at once,in that case we need to pass
group index and item index when passing which item is clicked
 */
data class NavGroupSelectedItem(
    val groupIndex: Int=-1,
    val itemIndex: Int=-1
)
@Composable
fun HomeScreenDrawerSheet(
    selectedItemIndex:NavGroupSelectedItem= NavGroupSelectedItem(),//group index,item index
    groups: List<ModalDrawerGroup>,
    onGroupClicked: (Int) -> Unit,
    onItemClicked: (groupIndex: Int, index: Int) -> Unit
) {

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
                            selected = selectedItemIndex==NavGroupSelectedItem(
                                groupIndex=groupIndex,
                                itemIndex=itemIndex
                            ),
                            onClick = {
                                onItemClicked(groupIndex, itemIndex)
                            },
                        )

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