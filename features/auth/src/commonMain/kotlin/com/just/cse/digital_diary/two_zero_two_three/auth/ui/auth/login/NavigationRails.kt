package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


data class NavigationRailState(
    val options: List<NavigationItem>,
    val selectedItem: Int,
    val isExpandMode: Boolean
)

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String = label
)

@Composable
fun NavigationRails(
    state: NavigationRailState,
    onSelectionChanged: (Int) -> Unit,
) {
    NavigationRails(
        selectedDestinationIndex = state.selectedItem,
        destinations = state.options,
        isExpanded = state.isExpandMode,
        onSelectionChanged = onSelectionChanged
    )

}

@Composable
fun NavigationRails(
    modifier: Modifier=Modifier,
    selectedDestinationIndex: Int,
    isExpanded: Boolean,
    destinations: List<NavigationItem>,
    onSelectionChanged: (Int) -> Unit,
) {

    NavigationRail(modifier) {
        destinations.forEachIndexed { index, item ->
            RailItem(
                icon = item.icon,
                label = item.label,
                selected = selectedDestinationIndex == index,
                isHorizontal = isExpanded,
                onClick = {
                    onSelectionChanged(index)
                }
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ColumnScope.RailItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    selected: Boolean,
    isHorizontal: Boolean,
) {
    if (isHorizontal) {
        Button(
            modifier = Modifier.align(Alignment.Start),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Icon(icon, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(label)
        }
    } else {
//        NavigationRailItem(
//            modifier = Modifier.align(Alignment.Start),
//            icon = {
//                Icon(
//                    modifier = Modifier.align(Alignment.Start),
//                    imageVector = icon, contentDescription = null
//                )
//            },
//            label = { Text(label) },
//            selected = selected,
//            onClick = onClick
//        )
        //Fix the selection indicator
        Button(
            modifier = Modifier.align(Alignment.Start),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Column{
                Icon(icon, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(label)
            }
        }
    }

}