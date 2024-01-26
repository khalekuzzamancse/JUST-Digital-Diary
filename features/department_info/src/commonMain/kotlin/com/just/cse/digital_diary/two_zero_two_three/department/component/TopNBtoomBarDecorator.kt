package com.just.cse.digital_diary.two_zero_two_three.department.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.bottom_navigation.BottomNavigationBar
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.department.component.state.TopNBottomBarDecoratorState


@Composable
internal fun TopNBottomBarDecorator(
    state: TopNBottomBarDecoratorState,
    onNavigationIconClick: () -> Unit,
    onDestinationSelected: (Int) -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = state.topBarTitle,
                navigationIcon = state.topNavigationIcon,
                onNavigationIconClick = onNavigationIconClick
            )
        },
        bottomBar = {
            BottomNavigationBar(
                destinations = state.bottomDestinations,
                selectedDestinationIndex = state.selectedDestination,
                onDestinationSelected = onDestinationSelected
            )
        }
    ) { scaffoldPadding ->
        content(Modifier.padding(scaffoldPadding))
    }
}

