package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.DrawerNavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationGroup
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.NavGroupSelectedItem

@Composable
fun Sheet(
    selectedDestinationIndex: Int = -1,
    header: @Composable () -> Unit,
    destinations: List<DrawerNavigationItem>,
    onDestinationSelected: (index: Int) -> Unit,
) {

    DrawerSheet(
        header = header,
        footer = null,
        destinations = destinations,
        destinationDecorator = { index ->
            val navigationItem = destinations[index]
            DrawerItemDecorator(
                item = navigationItem,
                isSelected = index == selectedDestinationIndex,
                onClick = {
                    onDestinationSelected(index)
                }

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
            val isSelected = selectedItemIndex == NavGroupSelectedItem(
                groupIndex = groupIndex,
                itemIndex = itemIndex
            )

            groups.getOrNull(groupIndex)?.let { group ->
                group.members.getOrNull(itemIndex)?.let { navigationItem ->
                    ItemDecorator(
                        navigationItem = navigationItem,
                        isSelected = isSelected,
                        onClick = {
                            onItemClicked(groupIndex, itemIndex)
                        }

                    )

                }
            }
        }
    )


}

@Composable
fun DrawerSheet(
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    destinations: List<DrawerNavigationItem>,
    destinationDecorator: @Composable (index: Int) -> Unit,
) {
    ModalDrawerSheet(
        modifier = Modifier,
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {
            if (header != null) {

                header()
            }
            destinations.forEachIndexed(
            ) { index, _ ->
                destinationDecorator(index)
            }
            if (footer != null) {
                footer()

            }
        }

    }

}

@Composable
fun DrawerSheet(
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    groups: List<NavigationGroup>,
    groupDecorator: @Composable (Int) -> Unit,
    itemDecorator: @Composable (groupIndex: Int, index: Int) -> Unit,
) {
    ModalDrawerSheet(
        modifier = Modifier,
    ) {
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


