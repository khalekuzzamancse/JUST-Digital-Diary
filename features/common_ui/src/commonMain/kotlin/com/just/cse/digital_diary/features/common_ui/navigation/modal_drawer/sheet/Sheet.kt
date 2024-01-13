package com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.sheet

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationGroup
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.NavGroupSelectedItem
@Composable
fun <T>Sheet(
    selectedDestinationIndex: Int =-1,
    visibilityDelay:Long,
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
            ItemDecorator(
                visibilityDelay = index*visibilityDelay,
                navigationItem=navigationItem,
                isSelected= index==selectedDestinationIndex ,
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
@Composable
fun <T>DrawerSheet(
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    destinations: List<NavigationItem<T>>,
    destinationDecorator: @Composable (index: Int) -> Unit,
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
            itemsIndexed(
                items = destinations,
                ) {index,_->
                destinationDecorator(index)
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


