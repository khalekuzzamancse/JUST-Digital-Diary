package com.just.cse.digital_diary.two_zero_two_three.common_ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.bottom_navigation.BottomNavigationBar


@Composable
fun TopBarDecorator(
    topBarTitle: String="",
    topNavigationIcon: ImageVector? = Icons.Default.Menu,
    onNavigationIconClick: () -> Unit={},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            DefaultTopAppbar(
                title = topBarTitle,
                navigationIcon = topNavigationIcon,
                onNavigationIconClick = onNavigationIconClick
            )
        }
    ) { scaffoldPadding ->
        content(scaffoldPadding)
    }
}
@Composable
fun <T> TopNBottomBarDecorator(
    topBarTitle: String,
    topNavigationIcon: ImageVector? = null,
    onNavigationIconClick: () -> Unit={},
    bottomDestinations: List<NavigationItemInfo<T>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
    content: @Composable (Modifier) -> Unit,
) {
    Scaffold(
        topBar = {
            DefaultTopAppbar(
                title = topBarTitle,
                navigationIcon = topNavigationIcon,
                onNavigationIconClick = onNavigationIconClick
            )
        },
        bottomBar = {
            BottomNavigationBar(
                destinations = bottomDestinations,
                selectedDestinationIndex = selectedDestinationIndex,
                onDestinationSelected = onDestinationSelected
            )
        }
    ) { scaffoldPadding ->
        content(Modifier.padding(scaffoldPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppbar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        navigationIcon, null
                    )
                }
            }

        },


    )

}