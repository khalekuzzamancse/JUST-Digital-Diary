package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Down
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Up
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.root_home.NavigatorManager
import com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination.EditProfile
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.about_us.AboutUsScreen
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.home.RootDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.home.RootHomeContent
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.home.topMostDestinations
import com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.message_from_vc.ViceChancellorMessageScreen
import com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination.CreateNoteScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopMostDestinationViewModel {
    private val _selectedIndex = MutableStateFlow(0)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
    }


}


class RootNavHost : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val navigator = LocalNavigator.current
        val navigatorManager = remember {
            NavigatorManager(navigator)
        }
        var drawerState by remember {
            mutableStateOf(DrawerState(DrawerValue.Closed))
        }
        val openDrawer: () -> Unit = {
            scope.launch {
                drawerState.open()
            }
        }
        val closedDrawer: () -> Unit = {
            scope.launch {
                drawerState = DrawerState(DrawerValue.Closed)
            }
        }


        val viewModel = remember { TopMostDestinationViewModel() }
        val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value

        ModalDrawerDecorator(
            visibilityDelay = 30,
            drawerState = drawerState,
            destinations = topMostDestinations,
            selectedDesertionIndex = currentDestinationIndex,
            onDestinationSelected = { selectedDestinationIndex ->
                viewModel.onSectionSelected(selectedDestinationIndex)
                closedDrawer()
            },

            content = {
                AnimatedContent(
                    targetState = currentDestinationIndex,
                    transitionSpec = {
                        slideIntoContainer(
                            animationSpec = tween(durationMillis = 300, easing = EaseIn),
                            towards = Up
                        ).togetherWith(
                            slideOutOfContainer(
                                animationSpec = tween(durationMillis = 300, easing = EaseIn),
                                towards = Down
                            )
                        )
                    }
                ){localDestination->
                    when (localDestination){
                        RootDestinations.HOME -> {
                            AnimateVisibilityDecorator {
                                RootHomeContent(
                                    openDrawer = openDrawer,
                                    onCreateNote = {
                                        navigator?.push(CreateNoteScreen())
                                    }
                                )
                            }

                        }
                        RootDestinations.MESSAGE_FROM_VC -> {
                            AnimateVisibilityDecorator {
                                ViceChancellorMessageScreen(
                                    onExitRequest = openDrawer
                                )
                            }
                        }

                        RootDestinations.ABOUT_US -> {
                            AnimateVisibilityDecorator {
                                AboutUsScreen(
                                    onExitRequest = openDrawer
                                )
                            }


                        }
                        RootDestinations.EditProfile -> {
                            AnimateVisibilityDecorator {
                                EditProfile(
                                    onExitRequest = openDrawer
                                )
                            }

                        }

                    }


                }
                when (currentDestinationIndex) {
                    RootDestinations.FACULTY_MEMBERS -> {
                        navigatorManager.navigateToFacultyModule()
                    }
                    RootDestinations.Notes -> {
                        navigatorManager.navigateToSharedNote()
                    }


                }
            }
        )

    }
}




