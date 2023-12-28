package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.FacultyModule
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.about_us.AboutUs
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.home.RootHomeContent
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.message_from_vc.ViceChancellorMessage
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.share_note.CreateNoteDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.share_note.CreateNoteScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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
        val navigator= LocalNavigator.current
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
                        RootHomeContent(
                            onCreateNote = {
                                navigator?.push(CreateNoteScreen())
                            }
                        )
                    }

                }
                1->{
                    AnimateVisibilityDecorator {
                        FacultyModule()

                    }

                }
                3->{
                    AnimateVisibilityDecorator {
                       ViceChancellorMessage()
                    }
                }
                4->{
                    AnimateVisibilityDecorator {
                        AboutUs()
                    }
                }

            }


        }
    }
}

