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
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.ModalDrawerDecoratorAnimationLess

/**
 * * Animation less
 * * useful when you use navigation component to switch betweeen destinatin

 */
@Composable
fun RootModuleDrawerAnimationLess(
    modifier: Modifier = Modifier,
    drawerHandler: ModalDrawerHandler,
    onEvent: (TopMostDestination) -> Unit,
    onLogOutRequest: () -> Unit = {},
    navHost: @Composable () -> Unit
) {

    LaunchedEffect(Unit) {
        drawerHandler.selectedSectionIndex.collect { destination ->
            when (destination) {
                RootDestinations.HOME -> onEvent(TopMostDestination.Home)
                RootDestinations.FACULTY_LIST -> onEvent(TopMostDestination.FacultyList)
                RootDestinations.ADMIN_OFFICE -> onEvent(TopMostDestination.AdminOffice)
                RootDestinations.MESSAGE_FROM_VC -> onEvent(TopMostDestination.MessageFromVC)
                RootDestinations.ABOUT_US -> onEvent(TopMostDestination.AboutUs)
                RootDestinations.Search -> onEvent(TopMostDestination.Search)
                RootDestinations.NOTE_LIST -> onEvent(TopMostDestination.NoteList)
                RootDestinations.EventGallery -> onEvent(TopMostDestination.EventGallery)
                RootDestinations.EXPLORE_JUST -> onEvent(TopMostDestination.ExploreJust)
            }

        }
    }
    ModalDrawerDecoratorAnimationLess(
        modifier = modifier,
        destinations = rootModuleTopMostDestinations,
        selectedDesertionIndex = drawerHandler.selectedSectionIndex.collectAsState().value,
        drawerState = drawerHandler.drawerState.collectAsState().value,
        onDestinationSelected = drawerHandler::onSectionSelected,
        header = {
            Header(
                name = "",
                department = "",
                onLogOutRequest = onLogOutRequest,
            )

        }
    ) {
        navHost()

    }
}


/**
 * * Has Animation
 * * useful when you use want to navigation direcly replacing composable instead of navigation component

 */
@Composable
fun RootModuleDrawer(
    modifier: Modifier = Modifier,
    drawerHandler: ModalDrawerHandler,
    onEvent: (TopMostDestination) -> Unit,
    onLogOutRequest: () -> Unit = {},
    navHost: @Composable() (AnimatedContentScope.(Int) -> Unit)
) {


    LaunchedEffect(Unit) {
        drawerHandler.selectedSectionIndex.collect { destination ->
            when (destination) {
                RootDestinations.HOME -> onEvent(TopMostDestination.Home)
                RootDestinations.FACULTY_LIST -> onEvent(TopMostDestination.FacultyList)
                RootDestinations.ADMIN_OFFICE -> onEvent(TopMostDestination.AdminOffice)
                RootDestinations.MESSAGE_FROM_VC -> onEvent(TopMostDestination.MessageFromVC)
                RootDestinations.ABOUT_US -> onEvent(TopMostDestination.AboutUs)
                RootDestinations.Search -> onEvent(TopMostDestination.Search)
                RootDestinations.NOTE_LIST -> onEvent(TopMostDestination.NoteList)
                RootDestinations.EventGallery -> onEvent(TopMostDestination.EventGallery)
                RootDestinations.EXPLORE_JUST -> onEvent(TopMostDestination.ExploreJust)
            }

        }
    }
    ModalDrawerDecorator(
        modifier = modifier,
        destinations = rootModuleTopMostDestinations,
        selectedDesertionIndex = drawerHandler.selectedSectionIndex.collectAsState().value,
        drawerState = drawerHandler.drawerState.collectAsState().value,
        onDestinationSelected = drawerHandler::onSectionSelected,
        header = {
            Header(
                name = "",
                department = "",
                onLogOutRequest = onLogOutRequest,
            )

        }
    ) {
        AnimatedContent(
            modifier = Modifier,
            targetState = drawerHandler.selectedSectionIndex.collectAsState().value,
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(durationMillis = 100, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Up
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(durationMillis = 100, easing = EaseIn),
                        towards = AnimatedContentTransitionScope.SlideDirection.Down
                    )
                )
            }
        ) { localDestination ->
            navHost(localDestination)
        }

    }
}