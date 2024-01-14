package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_navigation.BottomNavigationBar
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home.DepartmentTopAppbar


@Composable
internal fun <T> TopNBottomBarDecorator(
    topBarTitle: String,
    topNavigationIcon: ImageVector? = null,
    onNavigationIconClick: () -> Unit,
    bottomDestinations: List<NavigationItem<T>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
    content: @Composable (Modifier) -> Unit,
) {
    Scaffold(
        topBar = {
            DepartmentTopAppbar(
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

