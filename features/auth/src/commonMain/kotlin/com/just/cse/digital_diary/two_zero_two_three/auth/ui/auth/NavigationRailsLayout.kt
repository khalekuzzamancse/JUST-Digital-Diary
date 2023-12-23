package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationRails

//Scaffold need for snack bar
//No top bar and bottom bar,since it is for medium and expanded screen
//No navigation drawer allowed

//Completely Stateless Composable,


//Content itself should be any composable such as
//Scaffold if a snack-bar is needed.
data class NavigationRailGroup(
    val name: String,
    val icon: ImageVector? = null,
    val isVisible: Boolean = true,
    val items: List<NavigationItem>,
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun NavigationRailLayout(
    modifier: Modifier = Modifier,
    selectedDestinationIndex: Int,
    group: List<NavigationRailGroup>,
    onNavRailItemSelected: (Int) -> Unit,
    groupDecorator: @Composable (Int) -> Unit,
    itemDecorator: @Composable (Int) -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Row(modifier = Modifier) {
        NavigationRail(
            groups = group,
            groupDecorator = groupDecorator,
            itemDecorator = itemDecorator
        )
        content(Modifier.weight(1f))
    }

}

@Composable
fun NavigationRail(
    groups: List<NavigationRailGroup>,
    groupDecorator: @Composable (Int) -> Unit,
    itemDecorator: @Composable (Int) -> Unit,
) {
    groups.forEachIndexed { groupIndex, group ->
        groupDecorator(groupIndex)
        group.items.forEachIndexed{index, navigationItem ->
            itemDecorator(index)
        }
    }



}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun NavigationRailLayout(
    selectedDestinationIndex: Int,
    destinations: List<NavigationItem>,
    onNavRailItemSelected: (Int) -> Unit,
    snackBarMessage: String? = null,
    content: @Composable (Modifier) -> Unit
) {
    val screenType = calculateWindowSizeClass().widthSizeClass
    Row(modifier = Modifier) {
        NavigationRails(
            modifier = Modifier,
            destinations = destinations,
            selectedDestinationIndex = selectedDestinationIndex,
            isExpanded = screenType == WindowWidthSizeClass.Expanded,
            onSelectionChanged = onNavRailItemSelected
        )
        content(Modifier.weight(1f))
    }

}



