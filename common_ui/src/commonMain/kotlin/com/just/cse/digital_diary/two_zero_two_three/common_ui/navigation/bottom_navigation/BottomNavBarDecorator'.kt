package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.bottom_navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo

@Composable
fun <T> BottomBarDecorator(
    bottomDestinations: List<NavigationItemInfo<T>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
    topAppbar:@Composable () -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    Scaffold(
        topBar = topAppbar,
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