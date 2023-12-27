package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.top_most_home_destination.nav_graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.School
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.top_most_home_destination.RootHomeContent
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation.FacultyDestinationNavGraph
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


val topMostDestinations = listOf(
    NavigationItem(
        label = "Home",
        unFocusedIcon = Icons.Outlined.Home,
        key = ""
    ),
    NavigationItem(
        label = "Faculty Members",
        unFocusedIcon = Icons.Outlined.School,
        key = ""
    ),
    NavigationItem(
        label = "Administrators",
        unFocusedIcon = Icons.Outlined.AdminPanelSettings,
        key = ""
    ),

    NavigationItem(
        label = "Message(Vice -Chancellor)",
        unFocusedIcon = Icons.Outlined.Message,
        key = ""
    ),
    NavigationItem(
        label = "About Us",
        unFocusedIcon = Icons.Outlined.Info,
        key = ""
    ),

    )

@Composable
fun RootNavGraph() {
    Navigator(RootNavHost()) { navigator ->
        SlideTransition(navigator = navigator)

    }

}

class TopMostDestinationViewModel(val navigateToSection: (String) -> Unit = {}) {
    private val _selectedIndex = MutableStateFlow(0)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
    }


}

class RootNavHost : Screen {
    @Composable
    override fun Content() {
        val viewModel = remember { TopMostDestinationViewModel() }
        val currentDestinationIndex=viewModel.selectedSectionIndex.collectAsState().value
        ModalDrawerDecorator(
            destinations = topMostDestinations,
            selectedDesertionIndex =currentDestinationIndex ,
            onDestinationSelected = viewModel::onSectionSelected
        )
        {
            when (currentDestinationIndex){
                0->{
                    AnimateVisibilityDecorator {
                        RootHomeContent {}
                    }

                }
                1->{
                    AnimateVisibilityDecorator {
                        FacultyDestinationNavGraph()
                    }


                }

            }


        }
    }
}

