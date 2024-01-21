package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph.ModalDrawerHandler
import com.just.cse.digital_diary.two_zero_two_three.root_home.topMostDestinations

@Composable
fun DrawerDecorator(
    drawerHandler: ModalDrawerHandler,
    content: @Composable() (AnimatedContentScope.(Int) -> Unit)
) {
    ModalDrawerDecorator(
        visibilityDelay = 10,
        drawerState = drawerHandler.drawerState.collectAsState().value,
        destinations = topMostDestinations,
        selectedDesertionIndex = drawerHandler.selectedSectionIndex.collectAsState().value,
        onDestinationSelected = drawerHandler::onSectionSelected,
        content = {

                AnimatedContent(
                    modifier = Modifier,
                    targetState = drawerHandler.selectedSectionIndex.collectAsState().value,
                    transitionSpec = {
                        slideIntoContainer(
                            animationSpec = tween(durationMillis = 300, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Up
                        ).togetherWith(
                            slideOutOfContainer(
                                animationSpec = tween(durationMillis = 300, easing = EaseIn),
                                towards = AnimatedContentTransitionScope.SlideDirection.Down
                            )
                        )
                    }
                ) { localDestination ->
                    content(localDestination)
                }

        }
    )
}