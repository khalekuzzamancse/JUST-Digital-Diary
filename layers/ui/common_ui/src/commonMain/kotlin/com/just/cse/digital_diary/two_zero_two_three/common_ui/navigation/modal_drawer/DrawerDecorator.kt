package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.DrawerNavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.Sheet

//@Composable
//fun <T>ModalDrawerDecorator(
//    drawerState: ModalDrawerState,
//    visibilityDelay:Long = 0L,
//    destinations: List<NavigationItemInfo<T>>,
//    selectedDesertionIndex: Int,
//    onDestinationSelected: (Int) -> Unit = {},
//    header: @Composable () -> Unit,
//    content: @Composable () -> Unit,
//) {
//    ModalDrawer(
//        modifier = Modifier,
//        drawerState = drawerState.drawerState.collectAsState().value,
//        sheet = {
//            Sheet(
//                selectedDestinationIndex = selectedDesertionIndex,
//                header = header,
//                destinations = destinations
//            ) { index ->
//                onDestinationSelected(index)
//                drawerState.closeDrawer()
//            }
//        },
//        content=content
//    )
//}

/**
 * * Sheet appears has no animation
 */
@Composable
fun ModalDrawerDecoratorAnimationLess(
    modifier: Modifier = Modifier,
    destinations: List<DrawerNavigationItem>,
    selectedDesertionIndex: Int,
    drawerState: DrawerState,
    onDestinationSelected: (Int) -> Unit = {},
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {

    ModalDrawer(
        modifier = modifier,
        drawerState = drawerState,
        sheet = {
            AnimatedVisibility(
                visible = drawerState.currentValue == DrawerValue.Open,
            ) {
                Sheet(
                    selectedDestinationIndex = selectedDesertionIndex,
                    header = header,
                    destinations = destinations
                ) { index ->
                    onDestinationSelected(index)
                }
            }

        },
        content = content
    )
}


@Composable
fun ModalDrawerDecorator(
    modifier: Modifier = Modifier,
    destinations: List<DrawerNavigationItem>,
    selectedDesertionIndex: Int,
    drawerState: DrawerState,
    onDestinationSelected: (Int) -> Unit = {},
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {

    ModalDrawer(
        modifier = modifier,
        drawerState = drawerState,
        sheet = {
            AnimatedVisibility(
                visible = drawerState.currentValue == DrawerValue.Open,
            ) {
                Sheet(
                    selectedDestinationIndex = selectedDesertionIndex,
                    header = header,
                    destinations = destinations
                ) { index ->
                    onDestinationSelected(index)
                }
            }

        },
        content = content
    )
}

