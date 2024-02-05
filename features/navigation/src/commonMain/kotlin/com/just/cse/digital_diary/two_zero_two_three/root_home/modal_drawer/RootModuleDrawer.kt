package com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.root_home.NavigationEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.RootDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.ModalDrawerHandler
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.TopMostDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.rootModuleTopMostDestinations

@Composable
fun RootModuleDrawer(
    modifier: Modifier=Modifier,
    drawerHandler: ModalDrawerHandler,
    navigationEvent: NavigationEvent,
    onLogOutRequest:()->Unit={},
    navHost: @Composable() (AnimatedContentScope.(Int) -> Unit)
) {
    val navigateTo = navigationEvent.drawerDestinationNavigationRequest
//    var userInfo by remember { mutableStateOf(SignedInUser(name = "", department = "")) }
//    LaunchedEffect(Unit){
//        userInfo=SignedInUserRepository.getUserInfo()
//    }

    LaunchedEffect(Unit) {
        drawerHandler.selectedSectionIndex.collect { destination ->
            when (destination) {
                RootDestinations.HOME -> navigateTo(TopMostDestinations.HOME)
                RootDestinations.FACULTY_LIST -> navigateTo(TopMostDestinations.FACULTY_LIST)
                RootDestinations.ADMIN_OFFICE -> navigateTo(TopMostDestinations.ADMIN_OFFICES)
                RootDestinations.MESSAGE_FROM_VC -> navigateTo(TopMostDestinations.MESSAGE_FROM_VC)
                RootDestinations.ABOUT_US -> navigateTo(TopMostDestinations.ABOUT_US)
                RootDestinations.Search -> navigateTo(TopMostDestinations.SEARCH)
                RootDestinations.NOTE_LIST -> navigateTo(TopMostDestinations.NOTE_LIST)
                RootDestinations.EventGallery -> navigateTo(TopMostDestinations.EVENT_GALLERY)
                RootDestinations.EXPLORE_JUST -> navigationEvent.onWebsiteViewRequest("https://www.just.edu.bd")
            }
        }
    }
    ModalDrawerDecorator(
        modifier=modifier,
        header = {
            Header(
                name = "",
                department = "",
                onLogOutRequest = onLogOutRequest,
            )

        },
        visibilityDelay = 10,
        drawerState = drawerHandler.drawerState.collectAsState().value,
        destinations = rootModuleTopMostDestinations,
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
                navHost(localDestination)
            }

        }
    )
}