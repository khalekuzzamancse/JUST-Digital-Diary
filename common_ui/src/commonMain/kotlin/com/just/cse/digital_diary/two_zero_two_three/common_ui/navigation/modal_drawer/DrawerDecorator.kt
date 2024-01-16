package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.Sheet

@Composable
fun <T>ModalDrawerDecorator(
    drawerState: ModalDrawerState,
    visibilityDelay:Long = 70L,
    destinations: List<NavigationItemInfo<T>>,
    selectedDesertionIndex: Int,
    onDestinationSelected: (Int) -> Unit = {},
    content: @Composable () -> Unit,
) {
    ModalDrawer(
        modifier = Modifier,
        drawerState = drawerState.drawerState.collectAsState().value,
        sheet = {
            Sheet(
                visibilityDelay = visibilityDelay,
                selectedDestinationIndex = selectedDesertionIndex,
                destinations = destinations,
                onDestinationSelected = { index ->
                    onDestinationSelected(index)
                    drawerState.closeDrawer()
                }
            )
        },
        content=content
    )
}



@Composable
fun <T>ModalDrawerDecorator(
    destinations: List<NavigationItemInfo<T>>,
    visibilityDelay:Long = 70L,
    selectedDesertionIndex: Int,
    drawerState: DrawerState,
    onDestinationSelected: (Int) -> Unit = {},
    content: @Composable () -> Unit,
) {

    ModalDrawer(
        modifier = Modifier,
        drawerState = drawerState,
        sheet = {
           AnimatedVisibility(
               visible =drawerState.currentValue==DrawerValue.Open,
           ){
               Sheet(
                   visibilityDelay=visibilityDelay,
                   selectedDestinationIndex = selectedDesertionIndex,
                   destinations = destinations,
                   onDestinationSelected = { index ->
                       onDestinationSelected(index)
                   }
               )
           }

        },
        content=content
    )
}

