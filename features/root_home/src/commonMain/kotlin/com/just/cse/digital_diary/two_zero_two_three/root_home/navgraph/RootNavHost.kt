package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph

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
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.about_us.AboutUs
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.home.RootHomeContent
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.message_from_vc.ViceChancellorMessage
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.share_note.CreateNoteScreen
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
            println("RootNavHostClass:OpenDrawerRequest")
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
                when (currentDestinationIndex) {
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

                    RootDestinations.FACULTY_MEMBERS -> {
                        navigatorManager.navigateToFacultyModule()
                    }

                    RootDestinations.MESSAGE_FROM_VC -> {
                        AnimateVisibilityDecorator {
                            ViceChancellorMessage()
                        }
                    }

                    RootDestinations.ABOUT_US -> {
                        AnimateVisibilityDecorator {
                            AboutUs()
                        }


                    }
                }
            }
        )

    }
}




