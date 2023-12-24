package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem

/*
Any one can use it,
it should not and  need to bother in which section it going to use.
since it is navigation component so it will take the navigation items,
it might be better to have a tab with navigation groups,in that case
don't direcly modify it instead wrap it inside another composable and
add the addition functionality.
it responsibility:
notify which item is clicked,
it will not hold the selected item as state  because in case of orientation change
or other lifecycle events ,the state can be loss,


 */
@Composable
fun TabSection(
    modifier: Modifier,
    destinations: List<NavigationItem>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit //index of item
) {
    if (destinations.isNotEmpty()){
        Surface(
            modifier = modifier,
            shadowElevation = 10.dp,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedItem,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                destinations.forEachIndexed { index, navigationItem ->
                    Tab(text = { Text(navigationItem.label) },
                        selected = index == selectedItem,
                        onClick = {
                            onItemSelected(index)
                        }
                    )
                }
            }


        }
    }


}

@Composable
fun TabSection(
    modifier: Modifier,
    tabDestination: List<NavigationItem>,
    onNavigationIconClick: () -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = tabDestination.map { it.label }

    Surface(
        modifier = modifier,
        shadowElevation = 10.dp,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            Modifier.background(
                MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            IconButton(
                onClick = onNavigationIconClick,
            ) {
                Icon(
                    Icons.Filled.Menu, null
                )
            }
            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
        }

    }

}
