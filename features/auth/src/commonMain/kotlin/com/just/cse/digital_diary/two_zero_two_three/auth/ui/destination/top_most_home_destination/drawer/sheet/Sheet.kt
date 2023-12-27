package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.top_most_home_destination.drawer.sheet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer.DrawerSheet
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer.NavGroupSelectedItem


@Composable
fun <T>Sheet(
    selectedDestinationIndex: Int =-1,
    destinations: List<NavigationItem<T>>,
    onDestinationSelected: (index: Int) -> Unit
) {

    DrawerSheet(
        header = {
            Header(
                name="Md Abul Kalam",
                department = "CSE"
            )
        },
        footer = null,
        destinations = destinations,
        destinationDecorator = { index ->
            val navigationItem=destinations[index]
            NavigationDrawerItem(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                icon = {
                    Icon(
                        navigationItem.unFocusedIcon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = navigationItem.label,

                        )
                },
                selected = index==selectedDestinationIndex ,
                onClick = { onDestinationSelected(index) },
            )
        }
    )



}

@Composable
fun Sheet(
    selectedItemIndex: NavGroupSelectedItem = NavGroupSelectedItem(),//group index,item index
    groups: List<NavigationGroup>,
    onGroupClicked: (Int) -> Unit,
    onItemClicked: (groupIndex: Int, index: Int) -> Unit
) {
    DrawerSheet(
        header = {
            Header(
                name="Md Abul Kalam",
                department = "CSE"
            )
        },
        footer = null,
        groups = groups,
        groupDecorator = { groupIndex ->
            groups.getOrNull(groupIndex)?.let { group ->
                GroupDecorator(
                    groupName = group.name,
                    onClick = { onGroupClicked(groupIndex) },
                    icon = group.icon
                )
            }
        },
        itemDecorator = { groupIndex, itemIndex ->
            val isSelected= selectedItemIndex== NavGroupSelectedItem(
                groupIndex = groupIndex,
                itemIndex = itemIndex
            )

            groups.getOrNull(groupIndex)?.let { group ->
                group.members.getOrNull(itemIndex)?.let { navigationItem ->
                 ItemDecorator(
                     navigationItem=navigationItem,
                     isSelected=isSelected,
                     onClick = {
                         onItemClicked(groupIndex, itemIndex)
                     }

                 )

                }
            }
        }
    )



}
